package spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.domain.City;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CityDTO {
    private Integer id;
    private String name;

    public static CityDTO getInstance(City city) {
        return new CityDTO(city.getId(), city.getName());
    }
}
