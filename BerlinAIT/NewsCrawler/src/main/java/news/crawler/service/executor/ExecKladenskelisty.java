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
public class ExecKladenskelisty  implements Execute {
    @Override
    public List<EventDTO> execute(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        try {
            Document root = Jsoup.connect(config.getRootUrl()).get();
            Elements links = root.select("h4");
            for (Element element : links) {
                Element href = element.select("a").first();
                String title = href.text();
                String newsUrl = href.attr("href");

                log.info("Reading news from {}...", newsUrl);

                Document news = Jsoup.connect(newsUrl).get();
                Element cite = news.select("cite").first();
                String stringDate = cite.text();
                LocalDateTime dateTime = DateTimeUtils.convertDateTime(stringDate);

                String text = null;
                Element main = news.select("main").first();
                Elements paragraphs = main.select("p");
                for (Element p : paragraphs) {
                    if (!p.text().isEmpty()) {
                        text = (text != null? text + "\n" : "") + p.text();
                    }
                }
                String imgUrl = null;

                events.add(new EventDTO(title, newsUrl, dateTime, text, imgUrl));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}
