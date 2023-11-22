package news.crawler.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_config_id")
    private SourceConfig sourceConfig;

    private String title;
    private String newsUrl;
    private LocalDateTime dateTime;
    private String imageUrl;
    private String text;
}
