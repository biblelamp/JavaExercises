package com.onlineshop.controller;

import com.onlineshop.controller.dto.CountryDTO;
import com.onlineshop.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/country")
public class AdminCountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/all")
    private List<CountryDTO> findAll() {
        return countryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CountryDTO> addCountry(@RequestBody CountryDTO country) {
        CountryDTO newCountry = countryService.add(country);
        if (newCountry == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(newCountry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO country, @PathVariable Integer id) {
        CountryDTO updCountryDTO = countryService.update(id, country);
        if (updCountryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updCountryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CountryDTO> deleteCountry(@PathVariable Integer id) {
        CountryDTO delCountryDTO = countryService.delete(id);
        if (delCountryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delCountryDTO);
    }
}
