package de.alixcja.springboot.ecom_application.repository;

import de.alixcja.springboot.ecom_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  // findBy === select query
  // Active === field in product
  // True === value it should look for
  List<Product> findByActiveTrue();

  // Used for defining custom queries which are not provided by jpa
  // :keyword is the placeholder
  @Query("SELECT p FROM products p WHERE p.active = true AND p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<Product> searchProducts(@Param("keyword") String keyword);
}
