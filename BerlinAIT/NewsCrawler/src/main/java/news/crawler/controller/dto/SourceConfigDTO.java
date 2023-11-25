package news.crawler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import news.crawler.domain.SourceConfig;

@Setter
@Getter
@AllArgsConstructor
public class SourceConfigDTO {
    private Integer id;
    private String rootUrl;
    private String newsSuffix;
    private String className;
    private Boolean disabled;

    public static SourceConfigDTO getInstance(SourceConfig config) {
        return new SourceConfigDTO(config.getId(), config.getRootUrl(), config.getNewsSuffix(),
                config.getClassName(), config.getDisabled());
    }
}
