package com.example.emt_lab.model;
import com.example.emt_lab.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    private boolean goodCondition;

    @ManyToOne
    private Author author;
    private int availableCopies;

    public Book(String name, Category category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.goodCondition = true;

    }

    public Book() {

    }
}
