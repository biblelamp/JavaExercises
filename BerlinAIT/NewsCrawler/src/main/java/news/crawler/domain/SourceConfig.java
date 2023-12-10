package news.crawler.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SourceConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_config_id")
    private Integer id;

    private String rootUrl;
    private String newsSuffix;
    private String className;
    private Boolean disabled;

    public SourceConfig(String rootUrl, String newsSuffix) {
        this.rootUrl = rootUrl;
        this.newsSuffix = newsSuffix;
    }
}
