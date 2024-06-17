package com.example.course3.web;

import com.example.course3.Views.Views;
import com.example.course3.service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.course3.entities.Plant;
import com.example.course3.DTO.PlantDTO;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {
    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        Plant plant = plantService.getPlantByName(name);
        PlantDTO plantDTO = convertEntityToPlantDTO(plant);
        return plantDTO;
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    @GetMapping("/delivered/{id}")
    public Boolean delivered(@PathVariable Long id) {
        return plantService.delivered(id);
    }

    @GetMapping("/under-price/{price}")
    @JsonView(Views.Public.class)
    public List<Plant> plantsCheaperThan(@PathVariable BigDecimal price) {
        return plantService.findPlantsBelowPrice(price);
    }

    private static PlantDTO convertEntityToPlantDTO(Plant plant){
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant,plantDTO);
        return plantDTO;
    }
}
