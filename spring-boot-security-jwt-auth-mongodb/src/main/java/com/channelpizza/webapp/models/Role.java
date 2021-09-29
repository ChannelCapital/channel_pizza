package com.channelpizza.webapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

/**
 * Entity for roles
 */
@Document(collection = "roles")
public class Role {
  @Id
  private String id;

  @Size(max =32)
  @Indexed(unique = true)
  private ERole name;

  public Role() {
  }

  public Role(ERole name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }
}
