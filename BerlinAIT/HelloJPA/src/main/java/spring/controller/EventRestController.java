package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.domain.Event;
import spring.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable Integer id) {
        return eventService.get(id);
    }

    @PostMapping("/add")
    public Event add(@RequestBody Event event) {
        return eventService.add(event);
    }

    @PutMapping("/update/{id}")
    public Event update(@PathVariable Integer id, @RequestBody Event event) {
        return eventService.update(id, event);
    }

    @DeleteMapping("/delete/{id}")
    public Event delete(@PathVariable Integer id) {
        return eventService.delete(id);
    }
}
