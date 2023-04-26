package com.example.emt_lab.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
    public Country(Country country) {
        this.name = country.getName();
        this.continent = country.getContinent();
    }


    public Country() {

    }
}
