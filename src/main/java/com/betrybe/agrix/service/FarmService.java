package com.betrybe.agrix.service;

import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.exceptions.FarmNotFound;
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

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Create farm farm.
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
    return page.stream().toList();
  }

  /**
   * Update farm farm.
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
   * Delete farm farm.
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
}
