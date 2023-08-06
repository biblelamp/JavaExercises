package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domain.City;
import spring.repository.CityRepository;

@Service
public class CityService {

    static final Logger log = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;

    public City findOrCreateCityByName(String name) {
        City city = cityRepository.findByName(name);
        if (city == null) {
            city = new City();
            city.setName(name);
            cityRepository.save(city);
            log.info("City added: {}", city);
        }
        return city;
    }
}
