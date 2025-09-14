package de.alixcja.springboot.ecom_application.service;

import de.alixcja.springboot.ecom_application.dto.OrderItemDTO;
import de.alixcja.springboot.ecom_application.dto.OrderResponse;
import de.alixcja.springboot.ecom_application.model.*;
import de.alixcja.springboot.ecom_application.repository.OrderRepository;
import de.alixcja.springboot.ecom_application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  private final CartService cartService;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  public OrderService(CartService cartService, UserRepository userRepository, OrderRepository orderRepository) {
    this.cartService = cartService;
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
  }

  public Optional<OrderResponse> createOrder(String userId) {
    User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();

    List<CartItem> cartItems = cartService.getCart(userId);
    if (cartItems.isEmpty()) {
      return Optional.empty();
    }

    BigDecimal totalPrice = calculatePrice(cartItems);
    Order order = createOrder(user, totalPrice, cartItems);
    Order saved = orderRepository.save(order);

    cartService.clearCart(userId);

    return Optional.of(mapToOrderResponse(saved));
  }

  private static BigDecimal calculatePrice(List<CartItem> cartItems) {
    // .reduce kombiniert alle Werte im stream, links default value und rechts die Aktion
    return cartItems.stream()
            .map(CartItem::getPrice)
            // .reduce kombiniert alle Werte im stream, links default value und rechts die Aktion
            .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private static Order createOrder(User user, BigDecimal totalPrice, List<CartItem> cartItems) {
    Order order = new Order();
    order.setUser(user);
    order.setStatus(OrderStatus.CONFIRMED);
    order.setTotalAmount(totalPrice);
    List<OrderItem> orderItems = cartItems.stream()
            .map(item -> new OrderItem(item.getProduct(), item.getQuantity(), item.getPrice(), order)).toList();
    order.setItems(orderItems);
    return order;
  }

  private OrderResponse mapToOrderResponse(Order saved) {
    return new OrderResponse(saved.getId(),
            saved.getTotalAmount(),
            saved.getStatus(),
            saved.getItems().stream()
                    .map(OrderService::getOrderItemDTO).toList(),
            saved.getCreated());
  }

  private static OrderItemDTO getOrderItemDTO(OrderItem item) {
    return new OrderItemDTO(item.getId(),
            item.getProduct().getId(),
            item.getQuantity(),
            item.getPrice(),
            item.getPrice().multiply(new BigDecimal(item.getQuantity())));
  }
}
