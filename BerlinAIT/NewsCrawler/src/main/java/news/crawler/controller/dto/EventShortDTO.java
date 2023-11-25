package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import news.crawler.domain.Event;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class EventShortDTO {
    private String title;
    private String newsUrl;
    private LocalDateTime dateTime;
    private String source;

    public static EventShortDTO getInstance(Event event) {
        return new EventShortDTO(event.getTitle(), event.getNewsUrl(), event.getDateTime(), event.getSourceConfig().getRootUrl());
    }
}
