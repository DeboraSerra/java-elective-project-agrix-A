package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, float size) {

  /**
   * From entity farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }
}
