package com.example.course3.repository;

import com.example.course3.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {

  List<Plant> findByPriceLessThan(BigDecimal Price);

  Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivery);
}
