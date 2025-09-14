package de.alixcja.springboot.ecom_application.repository;

import de.alixcja.springboot.ecom_application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
