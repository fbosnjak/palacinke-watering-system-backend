package com.example.back.Controller;

import com.example.back.Entity.Plant;
import com.example.back.RequestBody.PlantHumidityUpdateRequest;
import com.example.back.RequestBody.PlantRequestBody;
import com.example.back.Service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/plant")
public class PlantController {
    private PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping(path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Boolean> add(@RequestBody PlantRequestBody request) {
        if (plantService.getPlantById(request.getId()).isPresent())
            return new ResponseEntity<>(false, HttpStatus.OK);

        Plant plant = new Plant(request.getId(), request.getName(), request.getMinHumidity(), request.getMaxHumidity(), 0);
        plantService.addPlant(plant);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Plant>> getAll() {
        return new ResponseEntity<>(plantService.getAllPlants(), HttpStatus.OK);
    }

    @GetMapping("/humidity/{plantId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Integer> getPlantHumidity(@PathVariable(value="plantId") String id) {
        if (plantService.getPlantById(id).isPresent()) {
            return new ResponseEntity<>(plantService.getPlantHumidity(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/humidity/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://192.168.43.133:8123")
    public ResponseEntity<Boolean> setPlantHumidity(@RequestBody PlantHumidityUpdateRequest request) throws URISyntaxException {
        if (plantService.getPlantById(request.getDevice()).isPresent()) {
            double primitiveDoubleHumidity = Double.parseDouble(request.getHumidity());
            int humidity = (int) primitiveDoubleHumidity;
            plantService.setPlantHumidity(request.getDevice(), humidity);

            Plant plant = plantService.getPlantById(request.getDevice()).get();
            if (plant.getCurrentHumidity() < plant.getMinHumidity()) {
                plantService.lowHumidityRequest("http://192.168.43.133:8123/humidity/low", request.getDevice());
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
            else {
                System.out.println(true);
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }}
}
