package com.onlineshop;

import com.onlineshop.controller.dto.CountryDTO;
import com.onlineshop.repository.CountryRepository;
import com.onlineshop.service.CountryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Order(1)
    public void findAllTest() {
        CountryDTO countryUSA = new CountryDTO(null, "USA");
        countryService.add(countryUSA);
        CountryDTO countryGermany = new CountryDTO(null, "Germany");
        countryService.add(countryGermany);

        List<CountryDTO> countries = countryService.findAll();

        Assertions.assertEquals(2, countries.size());
    }

    @Test
    @Order(2)
    public void addTest() {
        CountryDTO countryPoland = new CountryDTO(null, "Poland");
        CountryDTO countryAdded = countryService.add(countryPoland);

        Assertions.assertEquals("Poland", countryAdded.getCountryName());
    }

    @Test
    @Order(3)
    public void updateTest() {
        CountryDTO countryJapan = new CountryDTO(null, "Japan");
        CountryDTO countryUpdated = countryService.update(3, countryJapan);

        Assertions.assertEquals("Japan", countryUpdated.getCountryName());
    }

    @Test
    @Order(4)
    public void deleteTest() {
        CountryDTO countryDeleted = countryService.delete(3);

        Assertions.assertEquals("Japan", countryDeleted.getCountryName());
    }
}
