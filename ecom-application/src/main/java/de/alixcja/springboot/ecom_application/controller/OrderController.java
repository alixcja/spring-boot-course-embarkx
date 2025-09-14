package de.alixcja.springboot.ecom_application.controller;

import de.alixcja.springboot.ecom_application.dto.OrderResponse;
import de.alixcja.springboot.ecom_application.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") String userId) {
    return orderService.createOrder(userId)
            .map(orderResponse -> new ResponseEntity<>(orderResponse, HttpStatus.CREATED))
            .orElseGet(() -> ResponseEntity.badRequest().build());
  }
}
