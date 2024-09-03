package com.betrybe.agrix.service;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.exceptions.CropNotFound;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Create farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFound the farm not found
   */
  public Farm getFarmById(Long id) throws FarmNotFound {
    return farmRepository.findById(id).orElseThrow(FarmNotFound::new);
  }

  /**
   * Gets all farms.
   *
   * @param pageNum  the page num
   * @param pageSize the page size
   * @return the all farms
   */
  public List<Farm> getAllFarms(int pageNum, int pageSize) {
    Pageable pageable = PageRequest.of(pageNum, pageSize);
    Page<Farm> page = farmRepository.findAll(pageable);
    return page.toList();
  }

  /**
   * Update farm.
   *
   * @param id   the id
   * @param farm the farm
   * @return the farm
   * @throws FarmNotFound the farm not found
   */
  public Farm updateFarm(Long id, Farm farm) throws FarmNotFound {
    Farm farmDb = getFarmById(id);
    farmDb.setName(farm.getName());
    farmDb.setSize(farm.getSize());
    return farmRepository.save(farmDb);
  }

  /**
   * Delete farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFound the farm not found
   */
  public Farm deleteFarm(Long id) throws FarmNotFound {
    Farm farm = getFarmById(id);
    farmRepository.deleteById(id);
    return farm;
  }

  /**
   * Create crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   * @throws FarmNotFound the farm not found
   */
  public Crop createCrop(Long farmId, Crop crop) throws FarmNotFound {
    Farm farm = getFarmById(farmId);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Gets all crops by farm id.
   *
   * @param farmId the farm id
   * @return the all crops by farm id
   * @throws FarmNotFound the farm not found
   */
  public List<Crop> getAllCropsByFarmId(Long farmId) throws FarmNotFound {
    Farm farm = getFarmById(farmId);
    return farm.getCrops();
  }

  /**
   * Gets all crops.
   *
   * @param pageNum  the page num
   * @param pageSize the page size
   * @return the all crops
   */
  public List<Crop> getAllCrops(int pageNum, int pageSize) {
    Pageable pageable = PageRequest.of(pageNum, pageSize);
    Page<Crop> page = cropRepository.findAll(pageable);
    return page.toList();
  }

  /**
   * Gets crop by id.
   *
   * @param cropId the crop id
   * @return the crop by id
   * @throws CropNotFound the crop not found
   */
  public Crop getCropById(Long cropId) throws CropNotFound {
    return cropRepository.findById(cropId).orElseThrow(CropNotFound::new);
  }
}
