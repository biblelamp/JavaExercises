package news.crawler.service.executor;

import news.crawler.controller.dto.EventDTO;
import news.crawler.domain.SourceConfig;

import java.util.List;

public interface Execute {
    List<EventDTO> getNewsTitles(SourceConfig config);
    List<EventDTO> getNews(List<EventDTO> newsList);
}
