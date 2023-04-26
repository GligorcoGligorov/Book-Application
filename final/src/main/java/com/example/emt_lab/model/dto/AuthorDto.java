package com.example.emt_lab.model.dto;

import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.Country;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private Long country;

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
