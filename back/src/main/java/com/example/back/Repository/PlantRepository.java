package com.example.back.Repository;

import com.example.back.Entity.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends MongoRepository<Plant, String> {
}
