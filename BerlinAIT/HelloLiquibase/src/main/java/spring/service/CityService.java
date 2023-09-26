package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domain.City;
import spring.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City findByName(String name) {
        City city = cityRepository.findByName(name);
        if (city == null) {
            city = new City(null, name);
            city = cityRepository.save(city);
        }
        return city;
    }
}
