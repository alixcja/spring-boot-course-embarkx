package de.alixcja.springboot.ecom_application.repository;

import de.alixcja.springboot.ecom_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
