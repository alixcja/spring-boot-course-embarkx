package de.alixcja.springboot.ecom_application.dto;

import de.alixcja.springboot.ecom_application.model.Product;
import de.alixcja.springboot.ecom_application.model.User;

import java.math.BigDecimal;

public class CartItemResponse {
  private Long id;
  private User user;
  private Product product;
  private Integer quantity;
  private BigDecimal price;

  public CartItemResponse() {
  }

  public CartItemResponse(Long id, User user, Product product, Integer quantity, BigDecimal price) {
    this.id = id;
    this.user = user;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
