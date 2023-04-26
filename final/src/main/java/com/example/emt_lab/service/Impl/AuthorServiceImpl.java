package com.example.emt_lab.service.Impl;

import com.example.emt_lab.exceptions.AuthorNotFoundException;
import com.example.emt_lab.exceptions.CountryNotFoundException;
import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Country;
import com.example.emt_lab.model.dto.AuthorDto;
import com.example.emt_lab.repository.AuthorRepository;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {

        Country country1 = countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        this.authorRepository.deleteByName(name);
        Author author = new Author(name,surname,country1);
        this.authorRepository.save(author);
        // DO TUKA
        return Optional.of((author));
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country1 = countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        authorRepository.deleteByName(authorDto.getName());
        Author author = new Author(authorDto.getName(),authorDto.getSurname(),country1);
        authorRepository.save(author);

        return Optional.of(author);

    }


    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        Country country1 = countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));

        author.setCountry(country1);
        author.setName(name);
        author.setSurname(surname);

        authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    @Transactional
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        Country country1 = countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        author.setCountry(country1);
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());

        authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {

        this.authorRepository.deleteById(id);

    }
}
