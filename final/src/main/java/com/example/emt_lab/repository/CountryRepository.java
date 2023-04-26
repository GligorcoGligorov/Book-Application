package com.example.emt_lab.repository;

import com.example.emt_lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Optional<Country> findByName(String name);
    Optional<Country> findByContinent(String continent);

}
