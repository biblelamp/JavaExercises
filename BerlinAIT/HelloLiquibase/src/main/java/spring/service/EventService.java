package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.dto.EventDTO;
import spring.domain.City;
import spring.domain.Event;
import spring.repository.CityRepository;
import spring.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> result = new ArrayList<>(events.size());
        events.forEach(event -> result.add(EventDTO.getInstance(event)));
        return result;
    }

    public EventDTO add(EventDTO event) {
        City city = cityRepository.findByName(event.getCity());
        if (city != null) {
            Event newEvent = new Event(event.getName(), city);
            newEvent = eventRepository.save(newEvent);
            return EventDTO.getInstance(newEvent);
        }
        return null;
    }

    public EventDTO update(EventDTO event) {
        City city = cityRepository.findByName(event.getCity());
        if (city != null) {
            Event updEvent = eventRepository.findById(event.getId()).orElse(null);
            if (updEvent != null) {
                updEvent.setName(event.getName());
                updEvent.setCity(city);
                return EventDTO.getInstance(eventRepository.save(updEvent));
            }
        }
        return null;
    }

    public EventDTO delete(Integer eventId) {
        Event delEvent = eventRepository.findById(eventId).orElse(null);
        if (delEvent != null) {
            eventRepository.delete(delEvent);
            return EventDTO.getInstance(delEvent);
        }
        return null;
    }
}
