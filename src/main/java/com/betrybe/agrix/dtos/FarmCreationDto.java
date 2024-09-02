package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Farm;

/**
 * The type Farm creation dto.
 */
public record FarmCreationDto(String name, float size) {

  /**
   * To entity farm.
   *
   * @return the farm
   */
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
