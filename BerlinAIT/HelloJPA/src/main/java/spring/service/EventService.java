package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domain.Event;
import spring.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event add(Event event) {
        return eventRepository.save(event);
    }

    public Event findById(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.isPresent()? event.get() : null;
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public Event delete(Integer id) {
        Event event = findById(id);
        if (event != null) {
            eventRepository.delete(event);
        }
        return event;
    }
}
