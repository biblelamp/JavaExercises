package spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.controller.dto.EventDTO;
import spring.domain.City;
import spring.domain.Event;

public class DtoClassesTest {

    public static final int ID = 1;
    public static final String NAME = "Jazz Concert";
    public static final String CITY = "Berlin";

    @Test
    public void testEventDTO() {
        City city = new City(ID, CITY);
        Event event = new Event(ID, NAME, city);
        EventDTO eventDTO = EventDTO.getInstance(event);

        Assertions.assertEquals(ID, eventDTO.getId());
        Assertions.assertEquals(NAME, eventDTO.getName());
        Assertions.assertEquals(CITY, eventDTO.getCity());
    }
}
