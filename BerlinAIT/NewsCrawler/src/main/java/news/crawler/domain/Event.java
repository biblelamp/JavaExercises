package news.crawler.domain;

import java.time.OffsetDateTime;

public class Event {
    private Integer id;
    private String title;
    private String newsUrl;
    private OffsetDateTime dateTime;
    private String text;
    private String imageUrl;
}
