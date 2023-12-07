package spring.service;

import liquibase.repackaged.org.apache.commons.lang3.Validate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.dto.CityDTO;
import spring.domain.City;
import spring.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll();
        List<CityDTO> result = new ArrayList<>(cities.size());
        cities.forEach(city -> result.add(CityDTO.getInstance(city)));
        return result;
    }

    public CityDTO add(CityDTO cityDTO) {
        Validate.notNull(cityDTO.getName(), "The name of City must be defined.");
        if (cityRepository.findByName(cityDTO.getName()) == null) {
            City city = new City();
            city.setName(cityDTO.getName());
            city = cityRepository.save(city);
            CityDTO result = CityDTO.getInstance(city);
            log.info("City succesfully added. {}", result);
            return result;
        }
        log.error("City '{}' already exists.", cityDTO.getName());
        return null;
    }

    public CityDTO delete(Integer cityId) {
        City city = cityRepository.findById(cityId).orElse(null);
        if (city != null) {
            cityRepository.delete(city);
            CityDTO deleted = CityDTO.getInstance(city);
            log.info("City succesfully deleted. {}", city);
            return deleted;
        }
        log.error("City not found, cityId={}", cityId);
        return null;
    }
}
