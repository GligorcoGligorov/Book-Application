package com.example.emt_lab.service;

import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.Country;
import com.example.emt_lab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);
    Optional<Country> findByName(String name);
    Optional<Country> findByContinent(String continent);

    List<Country> findAll();

    Optional<Country> save(String name, String continent);
    Optional<Country> save(Country country);
    Optional<Country> edit(Long id, Country country);

    void deleteById(Long id);
}
