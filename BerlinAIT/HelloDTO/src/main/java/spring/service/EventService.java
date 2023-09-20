package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.dto.EventDTO;
import spring.domain.Event;
import spring.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> result = new ArrayList<>();
        //events.forEach(e -> result.add(EventDTO.getInstance(e)));
        for (Event e : events) {
            result.add(EventDTO.getInstance(e));
        }
        log.info("Reading {} records from Event table.", result.size());
        return result;
    }

    public EventDTO findById(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            EventDTO eventDTO = EventDTO.getInstance(event);
            log.info("Reading {} from Event table.", eventDTO);
            return eventDTO;
        }
        log.error("Not found event, eventId={} from Event table.", id);
        return null;
    }

    public EventDTO add(EventDTO e) {
        Event event = new Event(null, e.getName(), e.getCity());
        event = eventRepository.save(event);
        return EventDTO.getInstance(event);
    }

    public EventDTO update(EventDTO e) {
        Event event = eventRepository.findById(e.getId()).orElse(null);
        if (event != null) {
            event.setName(e.getName());
            event.setCity(e.getCity());
            event = eventRepository.save(event);
            return EventDTO.getInstance(event);
        }
        return null;
    }

    public EventDTO delete(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
            return EventDTO.getInstance(event);
        }
        return null;
    }
}
