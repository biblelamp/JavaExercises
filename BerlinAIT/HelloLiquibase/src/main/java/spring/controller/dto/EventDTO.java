package spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.domain.Event;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDTO {
    private Integer id;
    private String name;
    private String city;

    public static EventDTO getInstance(Event event) {
        return new EventDTO(event.getEventId(), event.getName(), event.getCity().getName());
    }
}
