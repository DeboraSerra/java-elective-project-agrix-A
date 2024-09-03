package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Crop;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(String name, Float plantedArea) {

  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea);
  }
}
