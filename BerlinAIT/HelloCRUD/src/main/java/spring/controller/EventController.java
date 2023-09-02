package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.domain.Event;
import spring.service.EventService;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String listForm(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "listEvent";
    }

    @GetMapping("/add")
    public String addEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @PostMapping("/add")
    public String addEvent(Model model, @ModelAttribute("event") Event event) {
        String name = event.getName();
        String city = event.getCity();

        if (!name.isEmpty() && !city.isEmpty()) {
            eventService.add(event);
            return "redirect:/";
        }

        model.addAttribute("errorMsg", "Event Name & City is required!");
        return "addEvent";
    }

    @GetMapping("/update/{id}")
    public String updateEventForm(Model model, @PathVariable Integer id) {
        Event event = eventService.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("id", id);
        return "updateEvent";
    }

    @PutMapping("/update/{id}")
    public String updateEvent(Model model, @ModelAttribute("event") Event event, @PathVariable Integer id) {
        String name = event.getName();
        String city = event.getCity();

        if (!name.isEmpty() && !city.isEmpty()) {
            eventService.update(id, event);
            return "redirect:/";
        }

        model.addAttribute("errorMsg", "Event Name & City is required!");
        return "updateEvent";
    }

    @DeleteMapping("/delete")
    public String deleteEvent(Integer id) {
        eventService.remove(id);
        return "redirect:/";
    }
}
