package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.SourceConfigRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SourceConfigService {

    @Autowired
    private SourceConfigRepository configRepository;

    public List<SourceConfigDTO> findAll() {
        List<SourceConfig> configs = configRepository.findAll();
        List<SourceConfigDTO> result = new ArrayList<>(configs.size());
        configs.forEach(i -> result.add(SourceConfigDTO.getInstance(i)));
        return result;
    }

    public SourceConfigDTO add(SourceConfigDTO config) {
        Validate.notNull(config.getRootUrl(), "Field rootUrl can't be null");
        Validate.notNull(config.getClassName(), "Field className can't be null");
        // TODO check if class exists
        SourceConfig source = new SourceConfig();
        return fillAndSave(config, source);
    }

    public SourceConfigDTO update(SourceConfigDTO config) {
        Validate.notNull(config.getId(), "Field id can't be null");
        SourceConfig source = configRepository.findById(config.getId()).orElse(null);
        if (source != null) {
            return fillAndSave(config, source);
        }
        log.error("Item from source_config table not found, configId={}", config.getId());
        return null;
    }

    public SourceConfigDTO delete(Integer configId) {
        SourceConfig source = configRepository.findById(configId).orElse(null);
        if (source != null) {
            configRepository.delete(source);
            return SourceConfigDTO.getInstance(source);
        }
        log.error("Item from source_config table not found, configId={}", configId);
        return null;
    }

    private SourceConfigDTO fillAndSave(SourceConfigDTO from, SourceConfig to) {
        to.setRootUrl(from.getRootUrl());
        to.setNewsSuffix(from.getNewsSuffix());
        to.setClassName(from.getClassName());
        to.setDisabled(from.getDisabled());
        to = configRepository.save(to);
        return SourceConfigDTO.getInstance(to);
    }
}
