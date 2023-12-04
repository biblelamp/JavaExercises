package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.controller.dto.EventDTO;
import spring.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public List<EventDTO> findAll() {
        List<EventDTO> events = eventService.findAll();
        return events;
    }

    @PostMapping("/add")
    public EventDTO add(@RequestBody EventDTO event) {
        return eventService.add(event);
    }

    @PutMapping("/update")
    public EventDTO update(@RequestBody EventDTO event) {
        return eventService.update(event);
    }

    @DeleteMapping("/delete/{eventId}")
    public EventDTO delete(@PathVariable Integer eventId) {
        return eventService.delete(eventId);
    }
}
