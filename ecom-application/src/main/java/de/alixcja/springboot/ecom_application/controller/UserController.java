package de.alixcja.springboot.ecom_application.controller;

import de.alixcja.springboot.ecom_application.dto.UserRequest;
import de.alixcja.springboot.ecom_application.dto.UserResponse;
import de.alixcja.springboot.ecom_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Used to set the base class path
@RequestMapping("/api/users")
public class UserController {

  // Will be auto injected by spring; also possible via constructor or with @RequiredArgsConstructor
  @Autowired
  private UserService userService;

  // One way to annotate the mapping:
   @GetMapping
  // Second way:
  // @RequestMapping(value = "/api/users", method = RequestMethod.GET)
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
  }

  // Fetch user via path variable, spring boot recognize the {id} as a dynamic path value
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
    return userService.fetchUserById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() ->
                    ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody UserRequest user) {
    userService.addUser(user);
    return ResponseEntity.ok("User successfully added");
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
    boolean updated = userService.updateUserById(id, user);
    if (!updated) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok("User successfully updated");
  }
}
