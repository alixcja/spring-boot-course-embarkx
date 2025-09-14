package de.alixcja.springboot.ecom_application.service;

import de.alixcja.springboot.ecom_application.dto.CartItemRequest;
import de.alixcja.springboot.ecom_application.dto.CartItemResponse;
import de.alixcja.springboot.ecom_application.model.CartItem;
import de.alixcja.springboot.ecom_application.model.Product;
import de.alixcja.springboot.ecom_application.model.User;
import de.alixcja.springboot.ecom_application.repository.CartItemRepository;
import de.alixcja.springboot.ecom_application.repository.ProductRepository;
import de.alixcja.springboot.ecom_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CartService {

  private final ProductRepository productRepository;
  private final CartItemRepository cartItemRepository;
  private final UserRepository userRepository;

  public CartService(ProductRepository productRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
    this.productRepository = productRepository;
    this.cartItemRepository = cartItemRepository;
    this.userRepository = userRepository;
  }

  public boolean addCart(String userId, CartItemRequest request) {
    // check if product exists
    Optional<Product> productOptional = productRepository.findById(request.getProductId());
    if (productOptional.isEmpty()) {
      return false;
    }

    // check if amount of product exists
    Product product = productOptional.get();
    if (product.getStockQuantity() < request.getQuantity()) {
      return false;
    }

    // check if user exists
    Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
    if (userOptional.isEmpty()) {
      return false;
    }
    User user = userOptional.get();

    CartItem existing = cartItemRepository.findByUserAndProduct(user, product);
    if (Objects.nonNull(existing)) {
      updateCartIteam(request, existing, product);
    } else {
      createNewCartItem(request, user, product);
    }
    return true;
  }

  private void updateCartIteam(CartItemRequest request, CartItem existing, Product product) {
    existing.setQuantity(existing.getQuantity() + request.getQuantity());
    existing.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existing.getQuantity())));
    cartItemRepository.save(existing);
  }

  private void createNewCartItem(CartItemRequest request, User user, Product product) {
    CartItem item = new CartItem();
    item.setUser(user);
    item.setProduct(product);
    item.setQuantity(request.getQuantity());
    item.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
    cartItemRepository.save(item);
  }

  public boolean deleteItemFromCart(String userId, Long productId) {
    // check if product exists
    Optional<Product> productOptional = productRepository.findById(productId);

    // check if user exists
    Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));

    if (productOptional.isPresent() && userOptional.isPresent()) {
      cartItemRepository.deleteByUserAndProduct(userOptional.get(), productOptional.get());
      return true;
    }

    return false;
  }

  public List<CartItemResponse> getByUser(String userId) {
    User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
    return cartItemRepository.findByUser(user)
            .stream()
            .map(this::mapToCartResponse)
            .toList();

  }

  public List<CartItem> getCart(String userId) {
    User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
    return cartItemRepository.findByUser(user);
  }

  private CartItemResponse mapToCartResponse(CartItem item) {
    CartItemResponse response = new CartItemResponse();
    response.setId(item.getId());
    response.setPrice(item.getPrice());
    response.setProduct(item.getProduct());
    response.setUser(item.getUser());
    response.setQuantity(item.getQuantity());
    return response;
  }

  public void clearCart(String userId) {
    User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
    cartItemRepository.deleteByUser(user);
  }
}
