package com.example.emt_lab.model.dto;

import com.example.emt_lab.enumerations.Category;
import com.example.emt_lab.model.Author;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    private String category;
    private Long author;
    private int availableCopies;
    private boolean goodCondition;


    public BookDto(String name, String category, Long author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.goodCondition = true;
    }
}
