package news.crawler.service;

import news.crawler.controller.dto.EventDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebService {

    public List<EventDTO> devByEvents(String rootUrl, String newsSuffix) throws IOException {
        List<EventDTO> events = new ArrayList<>();

        // scan url and extract news
        Document root = Jsoup.connect(rootUrl + newsSuffix).get();
        Elements links = root.select(".card__body");
        for (Element element : links) {
            Element href = element.select("a").first();
            String title = href.text();
            String newsUrl = href.attr("href");

            if (!newsUrl.startsWith("https")) {

                // read news page and extract date-time and text
                Document news = Jsoup.connect(rootUrl + newsUrl).get();
                String text = news.select(".article__container p").text();
                String newsData = news.getElementById("publishedAt").text();

                // TODO convert '17 ноября 2023, 15:39' -> LocalDateTime

                events.add(new EventDTO(title, newsUrl, null, text, null));
            }
        }

        return events;
    }

    public List<EventDTO> itWorldEvents(String rootUrl, String newsSuffix) throws IOException {
        List<EventDTO> events = new ArrayList<>();

        // read root url + news suffix and extract title news
        Document root = Jsoup.connect(rootUrl + newsSuffix).get();
        Elements links = root.select("h2");
        for (Element element : links) {
            Element href = element.select("a").first();
            String title = href.text();
            String newsUrl = href.attr("href");

            // read news page and extract date-time and text
            Document news = Jsoup.connect(rootUrl + newsUrl).get();
            String newsData = news.select(".news__text").first().text();
            String description = news.select(".article__lid").text();
            String text = news.select(".news-detail__content").text();

            // TODO need to create some time for sorting

            LocalDateTime dateTime = LocalDateTime.parse(newsData + " 00:00", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

            events.add(new EventDTO(title, rootUrl + newsUrl, dateTime, description + ' ' + text, null));
        }

        return events;
    }
}
