package com.betrybe.agrix.controller;

import com.betrybe.agrix.dtos.CropDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.exceptions.CropNotFound;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final FarmService farmService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public CropController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Gets all crops.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all crops
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "10") int pageSize
  ) {
    List<Crop> crops = farmService.getAllCrops(pageNumber, pageSize);
    List<CropDto> cropDtos = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropDtos);
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFound the crop not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) throws CropNotFound {
    Crop crop = farmService.getCropById(id);
    return ResponseEntity.status(HttpStatus.OK).body(CropDto.fromEntity(crop));
  }
}
