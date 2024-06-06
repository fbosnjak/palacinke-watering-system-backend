package com.example.back.RequestBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantRequestBody {
    private String id;
    private String name;
    private int minHumidity;
    private int maxHumidity;
}
