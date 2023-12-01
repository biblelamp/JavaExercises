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
public class ExecEKladensko implements Execute {
    @Override
    public List<EventDTO> execute(SourceConfig config) {
        List<EventDTO> events = new ArrayList<>();

        try {
            Document root = Jsoup.connect(config.getRootUrl() + config.getNewsSuffix()).get();
            Elements links = root.getElementsByClass("latestItemTitle");
            for (Element element : links) {
                Element href = element.select("a").first();
                String title = href.text();
                String newsUrl = config.getRootUrl() + href.attr("href");

                // read news page and extract date-time and text
                if (newsUrl.contains(config.getNewsSuffix())) {
                    log.info("Reading news from {}...", newsUrl);

                    Document news = Jsoup.connect(newsUrl).get();

                    LocalDateTime dateTime = null;
                    Element elementDateTime = news.getElementsByClass("itemHits").first();
                    if (elementDateTime != null) {
                        String[] newsDate = elementDateTime.text().split(" ");
                        dateTime = DateTimeUtils.convertDateTime(newsDate[0], newsDate[1]);
                    }

                    String imgUrl = null;
                    Element imgElement = news.getElementsByClass("itemImage").first();
                    if (imgElement != null) {
                        Element imageHref = imgElement.select("img").first();
                        imgUrl = config.getRootUrl() + imageHref.attr("src");
                    }
                    String text = null;
                    Element introtext = news.getElementsByClass("introtext").first();
                    if (introtext != null) {
                        text = introtext.text();
                    }
                    Element fulltext = news.getElementsByClass("fulltext").first();
                    if (fulltext != null) {
                        Elements paragraphs = fulltext.select("p");
                        for (Element p : paragraphs) {
                            if (!p.text().isEmpty()) {
                                text = text + "\n" + p.text();
                            }
                        }
                    }

                    events.add(new EventDTO(title, newsUrl, dateTime, text, imgUrl));
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return events;
    }
}
