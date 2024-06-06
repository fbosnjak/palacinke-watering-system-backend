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
    private int minHumidity;
    private int maxHumidity;
    private int currentHumidity;
}
