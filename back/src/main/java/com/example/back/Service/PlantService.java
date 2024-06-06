package com.example.back.Service;

import com.example.back.Entity.Plant;
import com.example.back.Repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {
    private PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {this.plantRepository = plantRepository;}

    public Optional<Plant> getPlantById(String id) {
        return plantRepository.findById(id);
    }

    public void addPlant(Plant plant) {
        plantRepository.save(plant);
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Integer getPlantHumidity(String id) {
        return plantRepository.findById(id).get().getCurrentHumidity();
    }

    public void setPlantHumidity(String id, int humidity) {
        Plant oldPlant = plantRepository.findById(id).get();
        Plant updatePlant = new Plant(oldPlant.getId(), oldPlant.getName(), oldPlant.getMinHumidity(), oldPlant.getMaxHumidity(), humidity);
        plantRepository.delete(oldPlant);
        plantRepository.save(updatePlant);
    }
}
