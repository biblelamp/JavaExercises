package com.onlineshop.repository;

import com.onlineshop.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findByCountryNameIgnoreCase(String countryName);
}
