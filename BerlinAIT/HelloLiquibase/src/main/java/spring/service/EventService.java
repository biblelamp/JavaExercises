package spring.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.dto.EventDTO;
import spring.domain.City;
import spring.domain.Event;
import spring.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityService cityService;

    private final ModelMapper modelMapper;

    public EventService() {
        modelMapper = new ModelMapper();
    }

    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> result = new ArrayList<>();
        events.forEach(e -> result.add(EventDTO.getInstance(e)));
        return result;
    }

    public EventDTO findById(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            //return EventDTO.getInstance(event);
            return modelMapper.map(event, EventDTO.class);
        }
        log.error("Event not found evendId: {}", id);
        return null;
    }

    public EventDTO add(EventDTO eventDTO) {
        Event newEvent = new Event();
        newEvent.setName(eventDTO.getName());
        City city = cityService.findOrCreateCityByName(eventDTO.getCity());
        newEvent.setCity(city);
        eventRepository.save(newEvent);
        EventDTO result = EventDTO.getInstance(newEvent);

        log.info("Event added: {}", result);
        return result;
    }

    public EventDTO update(Integer id, EventDTO eventDTO) {
        Event updEvent = eventRepository.findById(id).orElse(null);
        if (updEvent != null) {
            updEvent.setName(eventDTO.getName());
            City city = cityService.findOrCreateCityByName(eventDTO.getCity());
            updEvent.setCity(city);
            eventRepository.save(updEvent);

            log.info("Event updated: {}", updEvent);
            return EventDTO.getInstance(updEvent);
        }

        log.error("Event not found, eventId={}", id);
        return null;
    }

    public EventDTO delete(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
        }
        return EventDTO.getInstance(event);
    }
}
