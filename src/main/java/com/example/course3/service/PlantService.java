package com.example.course3.service;

import com.example.course3.entities.Plant;
import com.example.course3.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;
    public Plant getPlantByName(String name){
        return new Plant();
    }

    public Long save(Plant plant){
        Plant savedPlant = plantRepository.save(plant);
        return savedPlant.getId();
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price){
        return plantRepository.findByPriceLessThan(price);
    }

    public Boolean delivered(Long id){
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id,true);
    }
}
