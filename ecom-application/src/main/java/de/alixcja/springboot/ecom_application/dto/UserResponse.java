package de.alixcja.springboot.ecom_application.dto;

import de.alixcja.springboot.ecom_application.model.UserRole;

public class UserResponse {
  private String id;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  private UserRole role = UserRole.CUSTOMER;

  private AddressDto address;

  public UserResponse() {
  }

  public UserResponse(String id, String firstName, String lastName, String email, String phone, UserRole role, AddressDto address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public AddressDto getAddress() {
    return address;
  }

  public void setAddress(AddressDto address) {
    this.address = address;
  }
}
