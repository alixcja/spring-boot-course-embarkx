package de.alixcja.springboot.ecom_application;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Managed by spring boot; make it injectable dynamically
// Can also be done via interface
@Service
public class UserService {
  private final List<User> userList = new ArrayList<>();

  private Long nextUserId = 1L;

  public List<User> fetchAllUsers() {
    return userList;
  }

  public void addUser(User user) {
    user.setId(nextUserId);
    userList.add(user);
    nextUserId++;
  }

  public Optional<User> fetchUserById(Long id) {
    return userList.stream().filter(user -> user.getId().equals(id)).findFirst();
  }

  public boolean updateUserById(Long id, User user) {
    return userList.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .map(existingUser -> {
              existingUser.setFirstName(user.getFirstName());
              existingUser.setLastName(user.getLastName());
              return true;
            }).orElse(false);
  }
}
