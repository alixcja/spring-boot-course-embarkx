package de.alixcja.springboot.ecom_application;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Managed by spring boot; make it injectable dynamically
// Can also be done via interface
@Service
public class UserService {

  private final UserRepository userRepository;

  // Spring automatically passed the userRepository
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> fetchAllUsers() {
    return userRepository.findAll();
  }

  public void addUser(User user) {
    userRepository.save(user);
  }

  public Optional<User> fetchUserById(Long id) {
    return userRepository.findById(id);
  }

  public boolean updateUserById(Long id, User updatedUser) {
    return userRepository.findById(id)
            .map(existingUser -> {
              existingUser.setFirstName(updatedUser.getFirstName());
              existingUser.setLastName(updatedUser.getLastName());
              userRepository.save(existingUser);
              return true;
            }).orElse(false);
  }
}
