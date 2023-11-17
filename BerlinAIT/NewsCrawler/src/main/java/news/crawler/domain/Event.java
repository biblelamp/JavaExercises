package news.crawler.domain;

import java.time.OffsetDateTime;

public class Event {
    private Integer id;
    private String newsUrl;
    private String title;
    private OffsetDateTime dateTime;
    private String text;
}
