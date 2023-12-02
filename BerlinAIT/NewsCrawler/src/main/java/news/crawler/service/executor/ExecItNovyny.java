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
    public List<EventDTO> execute(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();
        Document doc = null;

        try {
            doc = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();
            Elements links = doc.select(".all-news__list a");

            for (Element element : links) {
                String newsUrl = element.attr("href");
                String title = element.select("span h3").text();
                if (!title.isEmpty()) {
                    log.info("Reading news from {}...", newsUrl);

                    Document page = Jsoup.connect(newsUrl).get();
                    String text = page.select(".content__wrapp p").text();
                    String dateTime = page.select(".content__info-create").text();
                    String photoUrl = page.select(".content__main-image img").attr("src");

                    LocalDateTime localDateTime = DateTimeUtils.convertDateTime(dateTime);

                    events.add(new EventDTO(title, newsUrl, localDateTime, text, photoUrl));
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}
