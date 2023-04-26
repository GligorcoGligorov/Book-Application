package com.example.emt_lab.service.Impl;

import com.example.emt_lab.enumerations.Category;
import com.example.emt_lab.exceptions.AuthorNotFoundException;
import com.example.emt_lab.exceptions.BookNotFoundException;
import com.example.emt_lab.exceptions.CountryNotFoundException;
import com.example.emt_lab.model.Author;
import com.example.emt_lab.model.Book;
import com.example.emt_lab.model.Country;
import com.example.emt_lab.repository.CountryRepository;
import com.example.emt_lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public Optional<Country> findByContinent(String continent) {
        return countryRepository.findByContinent(continent);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));

    }

    @Override
    public Optional<Country> edit(Long id, Country country) {
        Country country1 = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country1.setContinent(country.getContinent());
        country1.setName(country.getName());

        this.countryRepository.save(country1);

        return Optional.of(country1);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
