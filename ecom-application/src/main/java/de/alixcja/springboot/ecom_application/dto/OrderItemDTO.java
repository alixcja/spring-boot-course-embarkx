package de.alixcja.springboot.ecom_application.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
  private Long id;
  private Long productId;
  private Integer quantity;
  private BigDecimal price;
  private BigDecimal subTotal;

  public OrderItemDTO(Long id, Long productId, Integer quantity, BigDecimal price, BigDecimal multiply) {

  }
}
