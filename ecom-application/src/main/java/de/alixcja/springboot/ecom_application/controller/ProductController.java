package de.alixcja.springboot.ecom_application.controller;

import de.alixcja.springboot.ecom_application.dto.ProductRequest;
import de.alixcja.springboot.ecom_application.dto.ProductResponse;
import de.alixcja.springboot.ecom_application.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
    return ResponseEntity.ok(productService.searchProducts(keyword));
  }

  @PostMapping
  public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
    return new ResponseEntity<ProductResponse>(productService.createProduct(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
    return productService.updateProduct(id, request)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    boolean deleted = productService.deleteById(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
