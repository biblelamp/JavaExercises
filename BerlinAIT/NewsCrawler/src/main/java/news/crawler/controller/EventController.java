package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public List<EventDTO> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/check/{rootUrl}/{newsSuffix}/{className}")
    public List<EventDTO> checkParser(@PathVariable String rootUrl,
                                      @PathVariable String newsSuffix,
                                      @PathVariable String className) throws Exception {
        return eventService.checkParser(rootUrl, newsSuffix, className);
    }
}
