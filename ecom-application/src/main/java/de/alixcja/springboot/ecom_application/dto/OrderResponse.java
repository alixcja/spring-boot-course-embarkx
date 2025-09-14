package de.alixcja.springboot.ecom_application.dto;

import de.alixcja.springboot.ecom_application.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class OrderResponse {
  private Long id;
  private BigDecimal totalAmount;
  private OrderStatus status;
  private List<OrderItemDTO> items;
  private LocalDateTime createdAt;

  public OrderResponse(Long id, BigDecimal totalAmount, OrderStatus status, List<OrderItemDTO> items, LocalDateTime created) {
  }
}
