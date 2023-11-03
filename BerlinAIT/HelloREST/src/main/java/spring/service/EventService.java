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
        Event newEvent = new Event(event.getName(), event.getCity());
        return eventRepository.save(newEvent);
    }

    public Event update(Event event) {
        Event updEvent = eventRepository.findById(event.getId());
        if (updEvent != null) {
            updEvent.setName(event.getName());
            updEvent.setCity(event.getCity());
            return updEvent;
        }
        return null;
    }

    public Event delete(Integer eventId) {
        Event delEvent = eventRepository.findById(eventId);
        if (delEvent != null) {
            eventRepository.delete(delEvent);
            return delEvent;
        }
        return null;
    }
}
