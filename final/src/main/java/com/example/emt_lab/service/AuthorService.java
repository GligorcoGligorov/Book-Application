package com.example.emt_lab.service;

import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Country;
import com.example.emt_lab.model.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> findByName(String name);

    Optional<Author> save(String name, String surname, Long country);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, String name, String surname, Long country);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);

//    void refreshMaterializedView();
}
