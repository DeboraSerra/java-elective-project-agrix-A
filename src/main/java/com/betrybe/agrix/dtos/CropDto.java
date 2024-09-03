package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Crop;

/**
 * The type Crop dto.
 */
public record CropDto(Long id, String name, Float plantedArea, Long farmId) {

  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    FarmDto farmDto = crop.getFarm() != null ? FarmDto.fromEntity(crop.getFarm()) : null;
    assert farmDto != null;
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), farmDto.id());
  }
}
