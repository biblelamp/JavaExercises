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
@RequestMapping("/api")
public class ParseTestController {

    @Autowired
    private EventService eventService;

    @GetMapping("/parse/{rootUrl}/{newsSuffix}/{className}")
    public List<EventDTO> parseTest(@PathVariable String rootUrl,
                                    @PathVariable String newsSuffix,
                                    @PathVariable String className) throws Exception {
        return eventService.parseTest(rootUrl, newsSuffix, className);
    }
}
