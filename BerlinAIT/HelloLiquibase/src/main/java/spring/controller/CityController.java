package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.controller.dto.CityDTO;
import spring.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    public List<CityDTO> findAll() {
        return cityService.findAll();
    }

    @PostMapping("/add")
    public CityDTO add(@RequestBody CityDTO city) {
        return cityService.add(city);
    }

    @DeleteMapping("/delete/{cityId}")
    public CityDTO delete(Integer cityId) {
        return cityService.delete(cityId);
    }
}
