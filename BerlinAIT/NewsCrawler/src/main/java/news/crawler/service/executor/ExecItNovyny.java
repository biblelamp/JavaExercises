package news.crawler.service.executor;

import lombok.extern.slf4j.Slf4j;
import news.crawler.common.DateTimeUtils;
import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExecItNovyny implements Execute {

    @Override
    public List<EventDTO> getNewsTitles(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();
            Elements links = doc.select(".all-news__list a");

            for (Element element : links) {
                String title = element.select("span h3").text();
                String newsUrl = element.attr("href");

                if (newsUrl.startsWith("https")) {
                    events.add(new EventDTO(title, newsUrl));
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }

    @Override
    public List<EventDTO> getNews(List<EventDTO> newsList) {
        List<EventDTO> events = new ArrayList<>();

        try {
            for (EventDTO event : newsList) {
                log.info("Reading news from {}...", event.getNewsUrl());
                Document news = Jsoup.connect(event.getNewsUrl()).get();

                String imgUrl = news.select(".content__main-image img").attr("src");
                String dateTime = news.select(".content__info-create").text();
                String text = news.select(".content__wrapp p").text();

                LocalDateTime localDateTime = DateTimeUtils.convertDateTime(dateTime);

                events.add(new EventDTO(event.getTitle(), event.getNewsUrl(), imgUrl, localDateTime, text));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}
