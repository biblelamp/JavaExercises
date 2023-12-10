package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MvcController {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String webForm(Model model) {
        model.addAttribute("source", new SourceConfigDTO());
        return "webForm";
    }

    @PostMapping("/events")
    public String getEvents(Model model, @ModelAttribute("source") SourceConfigDTO source) throws Exception {
        List<EventDTO> events = eventService.checkParser(source);
        model.addAttribute("events", events);
        return "events";
    }
}
