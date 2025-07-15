package de.alixcja.springboot.ecom_application.controller;

import de.alixcja.springboot.ecom_application.dto.CartItemRequest;
import de.alixcja.springboot.ecom_application.dto.CartItemResponse;
import de.alixcja.springboot.ecom_application.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cart")
public class CartItemController {

  private final CartService cartService;

  public CartItemController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<List<CartItemResponse>> getCartByUser(@RequestHeader("X-User-ID") String userId) {
    return ResponseEntity.ok(cartService.getByUser(userId));
  }

  @PostMapping
  public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId, CartItemRequest request) {
    boolean created = cartService.addCart(userId, request);
    if (!created) {
      return ResponseEntity.badRequest().body("Product out of stock or user not found or product not found");
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/items/{productId}")
  public ResponseEntity<Void> removeFromCart(@PathVariable Long productId, @RequestHeader("X-User-ID") String userId) {
    boolean deleted = cartService.deleteItemFromCart(userId, productId);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
