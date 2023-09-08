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

    public Event add(Event event) {
        return eventRepository.save(event);
    }

    public Event findById(Integer id) {
        return eventRepository.findById(id);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public Event delete(Integer id) {
        return eventRepository.remove(id);
    }
}
