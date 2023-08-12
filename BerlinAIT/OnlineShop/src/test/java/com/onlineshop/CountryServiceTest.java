package com.onlineshop;

import com.onlineshop.controller.dto.CountryDTO;
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

    @Test
    @Order(1)
    public void addTest() {
        CountryDTO countryUSA = new CountryDTO(null, "USA");
        countryUSA = countryService.add(countryUSA);
        Assertions.assertEquals("USA", countryUSA.getCountryName());

        CountryDTO countryGermany = new CountryDTO(null, "Germany");
        countryGermany = countryService.add(countryGermany);
        Assertions.assertEquals("Germany", countryGermany.getCountryName());

        countryGermany = countryService.add(countryGermany);
        Assertions.assertNull(countryGermany);
    }

    @Test
    @Order(2)
    public void findAllTest() {
        List<CountryDTO> countries = countryService.findAll();
        Assertions.assertEquals(2, countries.size());
    }


    @Test
    @Order(3)
    public void updateTest() {
        CountryDTO countryJapan = new CountryDTO(null, "Japan");
        CountryDTO countryUpdated = countryService.update(2, countryJapan);
        Assertions.assertEquals("Japan", countryUpdated.getCountryName());

        CountryDTO notFound = countryService.update(3, countryJapan);
        Assertions.assertNull(notFound);
    }

    @Test
    @Order(4)
    public void deleteTest() {
        CountryDTO countryDeleted = countryService.delete(2);
        Assertions.assertEquals("Japan", countryDeleted.getCountryName());

        CountryDTO notFound = countryService.delete(3);
        Assertions.assertNull(notFound);
    }
}
