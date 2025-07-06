package de.alixcja.springboot.ecom_application.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/*@Data*/
// Usually should create getters and setters, but it did not work even after setting annotationProcessors
// Needed when lombok should create default constructor and other one with all attributes
// @NoArgsConstructor
// @AllArgsConstructor

// Entity represents a table in a relational database, instance of an entity represents a row in the table
@Entity(name = "user_table")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  private UserRole role = UserRole.CUSTOMER;

  // cascade: propagates all operation from user to addresses
  // orphanRemoval: removes addresses when user will also be deleted
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  // name: Name of column in user table, referencedColumnName: id column from address which will be put into `address_id`
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  // Default constructor is needed to create instances of entity class during retrieval of data
  public User() {
  }

  public User(Long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
