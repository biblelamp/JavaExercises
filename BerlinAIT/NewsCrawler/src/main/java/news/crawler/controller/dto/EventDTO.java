package news.crawler.controller.dto;

import lombok.Getter;
import lombok.Setter;
import news.crawler.domain.Event;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {
    private String title;
    private String newsUrl;
    private String imageUrl;
    private LocalDateTime dateTime;
    private String text;

    private String rootUrl;
    private String time;

    public EventDTO(String title, String newsUrl) {
        this.title = title;
        this.newsUrl = newsUrl;
    }

    public EventDTO(String title, String newsUrl, String rootUrl) {
        this.title = title;
        this.newsUrl = newsUrl;
        this.rootUrl = rootUrl;
    }

    public EventDTO(String title, String newsUrl, String rootUrl, String time) {
        this.title = title;
        this.newsUrl = newsUrl;
        this.rootUrl = rootUrl;
        this.time = time;
    }

    public EventDTO(String title, String newsUrl, String imageUrl, LocalDateTime dateTime, String text) {
        this.title = title;
        this.newsUrl = newsUrl;
        this.imageUrl = imageUrl;
        this.dateTime = dateTime;
        this.text = text;
    }

    public static EventDTO getInstance(Event event) {
        return new EventDTO(event.getTitle(), event.getNewsUrl(),
                event.getImageUrl(), event.getDateTime(), event.getText());
    }
}
