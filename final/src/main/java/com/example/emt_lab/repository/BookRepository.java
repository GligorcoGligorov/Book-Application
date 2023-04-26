package com.example.emt_lab.repository;

import com.example.emt_lab.enumerations.Category;
import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    Optional<Book> findByAuthor(Author author);
    Page<Book> findAll(Pageable pageable);
    void deleteByName(String name);
    void deleteById(Long id);

}
