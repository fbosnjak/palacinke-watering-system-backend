package com.example.back.RequestBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantHumidityUpdateRequest {
    private String device;
    private String humidity;
}