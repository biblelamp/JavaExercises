package com.onlineshop.controller.dto;

import com.onlineshop.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CountryDTO {
    private final Integer countryId;
    private final String countryName;

    public static CountryDTO getInstance(Country country) {
        return new CountryDTO(country.getCountryId(), country.getCountryName());
    }
}
