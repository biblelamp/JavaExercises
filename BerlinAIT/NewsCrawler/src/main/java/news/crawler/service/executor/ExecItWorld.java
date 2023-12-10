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
public class ExecItWorld implements Execute {

    @Override
    public List<EventDTO> getNewsTitles(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        try {
            Document root = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();
            Elements links = root.getElementsByClass("news-time");
            for (Element element : links) {
                Element href = element.selectFirst("a");
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");
                String time = element.getElementsByClass("news__time").first().text();

                events.add(new EventDTO(title, newsUrl, config.getRootUrl(), time));
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

                // get image url
                String imgUrl = news.getElementsByClass("news-detail__picture")
                        .select("a")
                        .attr("href");
                imgUrl =  event.getRootUrl() + imgUrl;

                // get date and create dateTime
                Element spanDate = news.selectFirst(".separator-line span");
                if (spanDate == null) {
                    spanDate = news.selectFirst(".color-silver span");
                }
                String newsDate = spanDate != null? spanDate.text() : "undefined";
                LocalDateTime dateTime = DateTimeUtils.convertDateTime(newsDate, event.getTime());

                // get news text
                String description = news.select(".article__lid").text();
                String text = description + " " + news.select(".news-detail__content").text();

                events.add(new EventDTO(event.getTitle(), event.getNewsUrl(), imgUrl, dateTime, text));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}
