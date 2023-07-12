package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Event;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    static final List<Event> events = new ArrayList<Event>(){{
        add(new Event("Opera", "London"));
        add(new Event("Violin concert", "Prague"));
        add(new Event("Jazz concert", "Berlin"));
        add(new Event("Art exhibition", "London"));
    }};

    public List<Event> findAll() {
        return events;
    }

    public void add(Event event) {
        events.add(event);
    }

    public Event get(Integer id) {
        return events.get(id);
    }

    public void update(Integer id, Event event) {
        Event updEvent = events.get(id);
        updEvent.setName(event.getName());
        updEvent.setCity(event.getCity());
    }

    public void remove(int id) {
        events.remove(id);
    }
}
