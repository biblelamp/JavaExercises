package news.crawler.controller;

import news.crawler.controller.dto.EventDTO;
import news.crawler.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebController {

    @Autowired
    private WebService webService;

    @GetMapping("/events")
    public List<EventDTO> getEvents() throws IOException {

//        String rootUrl = "https://www.it-world.ru";
//        String newsSuffix = "/news/";
//        return webService.itWorldEvents(rootUrl, newsSuffix);

        String rootUrl = "https://devby.io";
        String newsSuffix = "/news/";
        return webService.devByEvents(rootUrl, newsSuffix);
    }
}
