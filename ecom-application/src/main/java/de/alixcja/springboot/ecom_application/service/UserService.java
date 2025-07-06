package de.alixcja.springboot.ecom_application.service;

import de.alixcja.springboot.ecom_application.dto.AddressDto;
import de.alixcja.springboot.ecom_application.dto.UserRequest;
import de.alixcja.springboot.ecom_application.dto.UserResponse;
import de.alixcja.springboot.ecom_application.model.Address;
import de.alixcja.springboot.ecom_application.repository.UserRepository;
import de.alixcja.springboot.ecom_application.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

  public List<UserResponse> fetchAllUsers() {
    List<User> allUsers = userRepository.findAll();
    return allUsers.stream().map(this::mapToUserResponse).toList();
  }

  public void addUser(UserRequest userRequest) {
    User user = new User();
    updateUserFromRequest(user, userRequest);
    userRepository.save(user);
  }

  private void updateUserFromRequest(User user, UserRequest request) {
    user.setFirstName(request.getFirstName());
    user.setPhone(request.getPhone());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    if (Objects.nonNull(request.getAddress())) {
      Address address = new Address();
      address.setCity(request.getAddress().getCity());
      address.setCountry(request.getAddress().getCountry());
      address.setStreet(request.getAddress().getStreet());
      address.setZipcode(request.getAddress().getZipcode());
      address.setState(request.getAddress().getState());
      user.setAddress(address);
    }
  }

  public Optional<UserResponse> fetchUserById(Long id) {
    return userRepository.findById(id).map(this::mapToUserResponse);
  }

  public boolean updateUserById(Long id, UserRequest updatedUser) {
    return userRepository.findById(id)
            .map(existingUser -> {
              updateUserFromRequest(existingUser, updatedUser);
              userRepository.save(existingUser);
              return true;
            }).orElse(false);
  }

  private UserResponse mapToUserResponse(User user) {
    UserResponse response = new UserResponse();
    response.setId(String.valueOf(user.getId()));
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    response.setEmail(user.getEmail());
    response.setPhone(user.getPhone());

    if (Objects.nonNull(user.getAddress())) {
      AddressDto dto = new AddressDto();
      dto.setCity(user.getAddress().getCity());
      dto.setCountry(user.getAddress().getCountry());
      dto.setState(user.getAddress().getState());
      dto.setStreet(user.getAddress().getStreet());
      dto.setZipcode(user.getAddress().getZipcode());
      response.setAddress(dto);
    }
    return response;
  }
}
