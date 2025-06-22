package de.alixcja.springboot.ecom_application;

import lombok.Data;

// Usually should create getters and setters, but it did not work even after setting annotationProcessors
/*@Data*/
public class User {
  private Long id;
  private String firstName;
  private String lastName;

  public User() {
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
}
