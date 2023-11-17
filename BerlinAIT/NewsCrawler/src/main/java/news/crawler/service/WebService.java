package news.crawler.service;

import news.crawler.controller.dto.EventDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebService {
    public List<EventDTO> getEvents(String sourceUrl) throws IOException {
        List<EventDTO> events = new ArrayList<>();

        // scan url and extract news
        Document doc = Jsoup.connect(sourceUrl).get();
        Elements links = doc.select("h2");
        for (Element element : links) {
            Element href = element.select("a").first();
            String title = href.text();
            String url = href.attr("href");
            events.add(new EventDTO(title, url));
        }

        return events;
    }
}
