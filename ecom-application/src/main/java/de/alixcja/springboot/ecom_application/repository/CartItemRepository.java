package de.alixcja.springboot.ecom_application.repository;

import de.alixcja.springboot.ecom_application.model.CartItem;
import de.alixcja.springboot.ecom_application.model.Product;
import de.alixcja.springboot.ecom_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  CartItem findByUserAndProduct(User user, Product product);

  void deleteByUserAndProduct(User user, Product product);

  List<CartItem> findByUser(User user);
}
