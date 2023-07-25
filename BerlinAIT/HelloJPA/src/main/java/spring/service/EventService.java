package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domain.Event;
import spring.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event get(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event add(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Integer id, Event event) {
        Event updEvent = get(id);
        if (updEvent != null) {
            updEvent.setName(event.getName());
            updEvent.setCity(event.getCity());
            eventRepository.save(updEvent);
        }
        return updEvent;
    }

    public Event delete(Integer id) {
        Event event = get(id);
        if (event != null) {
            eventRepository.delete(event);
        }
        return event;
    }
}
