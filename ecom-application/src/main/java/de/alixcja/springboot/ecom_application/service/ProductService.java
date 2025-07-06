package de.alixcja.springboot.ecom_application.service;

import de.alixcja.springboot.ecom_application.dto.ProductRequest;
import de.alixcja.springboot.ecom_application.dto.ProductResponse;
import de.alixcja.springboot.ecom_application.model.Product;
import de.alixcja.springboot.ecom_application.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ProductResponse createProduct(ProductRequest request) {
    Product product = new Product();
    updateProductFromRequest(product, request);
    Product saved = productRepository.save(product);
    return mapToProductResponse(saved);
  }

  private ProductResponse mapToProductResponse(Product saved) {
    return new ProductResponse(saved.getId(), saved.getName(), saved.getDescription(),
            saved.getPrice(), saved.getStockQuantity(), saved.getCategory(), saved.getImageUrl(), saved.getActive());
  }

  private void updateProductFromRequest(Product product, ProductRequest request) {
    product.setName(request.getName());
    product.setCategory(request.getCategory());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setImageUrl(request.getImageUrl());
    product.setStockQuantity(request.getStockQuantity());
  }

  public Optional<ProductResponse> updateProduct(Long id, ProductRequest request) {
    return productRepository.findById(id)
            .map(existing -> {
              updateProductFromRequest(existing, request);
              Product saved = productRepository.save(existing);
              return mapToProductResponse(saved);
            });
  }
}
