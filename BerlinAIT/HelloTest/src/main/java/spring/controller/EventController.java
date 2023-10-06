package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.controller.dto.EventDTO;
import spring.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public List<EventDTO> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDTO findById(@PathVariable Integer id) {
        return eventService.findById(id);
    }

    @PostMapping("/add")
    public EventDTO add(@RequestBody EventDTO event) {
        return eventService.add(event);
    }

    @PutMapping("/update")
    public EventDTO update(@RequestBody EventDTO event) {
        return eventService.update(event);
    }

    @DeleteMapping("/delete/{id}")
    public EventDTO delete(@PathVariable Integer id) {
        return eventService.delete(id);
    }
}
