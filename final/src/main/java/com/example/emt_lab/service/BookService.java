package com.example.emt_lab.service;

import com.example.emt_lab.enumerations.Category;
import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.dto.AuthorDto;
import com.example.emt_lab.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    List<Category> listAllCategories();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, String category, Long author, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, String category, Long author, int availableCopies, boolean goodCondition);

    Optional<Book> edit(Long id, BookDto bookDto);

    Optional<Book> isTaken(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);

    void deleteById(Long id);
    void deleteByName(String name);

}
