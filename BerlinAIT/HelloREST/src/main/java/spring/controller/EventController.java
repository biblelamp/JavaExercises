package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.domain.Event;
import spring.service.EventService;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public List<Event> findAll() {
        List<Event> events = eventService.findAll();
        return events;
    }

    @PostMapping("/add")
    public Event add(@RequestBody Event event) {
        return eventService.add(event);
    }

    @PutMapping("/update")
    public Event update(@RequestBody Event event) {
        return eventService.update(event);
    }

    @DeleteMapping("/delete/{eventId}")
    public Event delete(@PathVariable Integer eventId) {
        return eventService.delete(eventId);
    }
}
