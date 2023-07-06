package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.domain.Event;
import spring.service.EventService;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEvent(Model model, @ModelAttribute("event") Event event) {

        String name = event.getName();
        String city = event.getCity();

        if (name != null && name.length() > 0 && city != null && city.length() > 0) {
            Event newEvent = new Event(name, city);
            eventService.add(newEvent);

            return "redirect:/";
        }
        model.addAttribute("errorMessage", "Event Name & City is required!");
        return "add";
    }
}
