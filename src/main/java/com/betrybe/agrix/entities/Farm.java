package com.betrybe.agrix.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Farm.
 */
@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Float size;

  /**
   * Instantiates a new Farm.
   */
  public Farm() {
  }

  /**
   * Instantiates a new Farm.
   *
   * @param name the name
   * @param size the size
   */
  public Farm(String name, Float size) {
    this.name = name;
    this.size = size;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public Float getSize() {
    return size;
  }

  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(Float size) {
    this.size = size;
  }
}
