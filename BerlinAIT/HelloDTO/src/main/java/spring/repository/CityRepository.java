package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

    City findByName(String name);
}
