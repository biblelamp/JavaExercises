package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    private String title;
    private String newsUrl;
    private LocalDateTime dateTime;
    private String text;
    private String imageUrl;
}
