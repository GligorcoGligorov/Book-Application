package com.example.emt_lab.service.Impl;

import com.example.emt_lab.enumerations.Category;
import com.example.emt_lab.events.BookCreatedEvent;
import com.example.emt_lab.exceptions.AuthorNotFoundException;
import com.example.emt_lab.exceptions.BookNotFoundException;
import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.dto.BookDto;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.repository.BookRepository;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Category> listAllCategories() {
        return Arrays.asList(Category.values());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, String category, Long author, int availableCopies) {
        try {
            Author author1 = authorRepository.findById(author).orElseThrow(() -> new AuthorNotFoundException(author));
            this.bookRepository.deleteByName(name);
            Category category1 = Category.valueOf(category);
            Book book = new Book(name,category1,author1,availableCopies);
            this.bookRepository.save(book);
            this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
            return Optional.of((book));
        }catch (Exception e) {
            // handle the exception here
            return Optional.empty();
        }

    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        try {
            Author author1 = authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
            this.bookRepository.deleteByName(bookDto.getName());
            Category category1 = Category.valueOf(bookDto.getCategory());
            Book book = new Book(bookDto.getName(),category1,author1,bookDto.getAvailableCopies());
            this.bookRepository.save(book);
            this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
            return Optional.of(book);
        } catch (Exception e) {
            // handle the exception here
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, String category, Long author, int availableCopies, boolean goodCondition) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Author author1 = authorRepository.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));

        Category category1 = Category.valueOf(category);


        book.setAuthor(author1);
        book.setName(name);
        book.setCategory(category1);
        book.setAvailableCopies(availableCopies);
        book.setGoodCondition(goodCondition);

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Author author1 = authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        Category category1 = Category.valueOf(bookDto.getCategory());


        book.setAuthor(author1);
        book.setName(bookDto.getName());
        book.setCategory(category1);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setGoodCondition(bookDto.isGoodCondition());

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> isTaken(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));


        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        bookRepository.deleteByName(name);
    }
}
