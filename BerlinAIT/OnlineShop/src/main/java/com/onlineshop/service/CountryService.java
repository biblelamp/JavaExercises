package com.onlineshop.service;

import com.onlineshop.controller.dto.CountryDTO;
import com.onlineshop.domain.Country;
import com.onlineshop.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDTO> findAll() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> result = new ArrayList<>();
        countries.forEach(country -> result.add(CountryDTO.getInstance(country)));
        return result;
    }

    public CountryDTO add(CountryDTO country) {
        // control: if this name exists
        List<Country> countries = countryRepository.findByCountryNameIgnoreCase(country.getCountryName());
        if (!countries.isEmpty()) {
            log.error("Duplicate Name of Country: {}", country.getCountryName());
            return null;
        }
        Country newCountry = new Country();
        newCountry.setCountryName(country.getCountryName());
        newCountry = countryRepository.save(newCountry);
        log.info("Country {} successfully added.", country.getCountryName());
        return CountryDTO.getInstance(newCountry);
    }

    public CountryDTO update(Integer id, CountryDTO countryDTO) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            Country updCountry = country.get();
            // TODO control: if this name exists
            updCountry.setCountryName(countryDTO.getCountryName());
            updCountry = countryRepository.save(updCountry);
            return CountryDTO.getInstance(updCountry);
        }
        log.error("Not found Country {} countryId: {}", countryDTO.getCountryName(), id);
        return null;
    }

    public CountryDTO delete(Integer id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            Country delCountry = country.get();
            countryRepository.delete(delCountry);
            return CountryDTO.getInstance(delCountry);
        }
        log.error("Not found Country countryId: {}", id);
        return null;
    }
}
