package de.alixcja.springboot.ecom_application.controller;

import de.alixcja.springboot.ecom_application.dto.ProductRequest;
import de.alixcja.springboot.ecom_application.dto.ProductResponse;
import de.alixcja.springboot.ecom_application.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
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

}
