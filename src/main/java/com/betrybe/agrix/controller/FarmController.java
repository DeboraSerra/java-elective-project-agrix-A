package com.betrybe.agrix.controller;

import com.betrybe.agrix.dtos.CropCreationDto;
import com.betrybe.agrix.dtos.CropDto;
import com.betrybe.agrix.dtos.FarmCreationDto;
import com.betrybe.agrix.dtos.FarmDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param body the body
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmCreationDto body) {
    Farm farm = farmService.createFarm(body.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(farm));
  }

  /**
   * Gets all.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAll(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "10") int pageSize
  ) {
    List<Farm> farms = farmService.getAllFarms(pageNumber, pageSize);
    List<FarmDto> farmDtos = farms.stream().map(FarmDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(farmDtos);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFound the farm not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) throws FarmNotFound {
    Farm farm = farmService.getFarmById(id);
    return ResponseEntity.status(HttpStatus.OK).body(FarmDto.fromEntity(farm));
  }

  /**
   * Update farm response entity.
   *
   * @param id   the id
   * @param body the body
   * @return the response entity
   * @throws FarmNotFound the farm not found
   */
  @PutMapping("/{id}")
  public ResponseEntity<FarmDto> updateFarm(@PathVariable Long id, FarmCreationDto body)
      throws FarmNotFound {
    Farm farm = farmService.updateFarm(id, body.toEntity());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(FarmDto.fromEntity(farm));
  }

  /**
   * Delete farm by id response entity.
   *
   * @param id the id
   * @return the response entity
   * @throws FarmNotFound the farm not found
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<FarmDto> deleteFarmById(@PathVariable Long id) throws FarmNotFound {
    Farm farm = farmService.deleteFarm(id);
    return ResponseEntity.status(HttpStatus.OK).body(FarmDto.fromEntity(farm));
  }

  /**
   * Create crop response entity.
   *
   * @param farmId the farm id
   * @param body   the body
   * @return the response entity
   * @throws FarmNotFound the farm not found
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long farmId,
      @RequestBody CropCreationDto body)
      throws FarmNotFound {
    Crop crop = farmService.createCrop(farmId, body.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(crop));
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   * @throws FarmNotFound the farm not found
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId)
      throws FarmNotFound {
    List<Crop> crops = farmService.getAllCropsByFarmId(farmId);
    List<CropDto> cropDtos = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropDtos);
  }
}
