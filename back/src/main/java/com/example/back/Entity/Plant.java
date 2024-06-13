package com.example.back.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Plant {
    @Id
    private String id;
    private String name;
    private int minHumidity = -1;
    private int maxHumidity = -1;
    private int currentHumidity = -1;

    public Plant(String id) {
        this.id = id;
    }
}
