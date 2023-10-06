package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByName(String name);
}
