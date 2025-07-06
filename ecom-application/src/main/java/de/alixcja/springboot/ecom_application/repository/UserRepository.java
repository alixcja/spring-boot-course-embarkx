package de.alixcja.springboot.ecom_application.repository;

import de.alixcja.springboot.ecom_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface that allows to perform various database operation without much boilerplate,
// since jpa provides predefined / default methods like findAll(), findById(), ...
// - needs to be annotated with @Repository to become a spring managed component
// user is entity, type of primary key is Long
// JpaRepository is recommended due better performance and some more jpa methods like flush, batch, etc
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
