package de.alixcja.springboot.ecom_application;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}
