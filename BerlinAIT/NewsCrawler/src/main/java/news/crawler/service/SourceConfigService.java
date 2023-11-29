package news.crawler.service;

import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.SourceConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        SourceConfig source = new SourceConfig();
        return fillAndSave(config, source);
    }

    public SourceConfigDTO update(SourceConfigDTO config) {
        SourceConfig source = configRepository.findById(config.getId()).orElse(null);
        if (source != null) {
            return fillAndSave(config, source);
        }
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

    public SourceConfigDTO delete(Integer configId) {
        SourceConfig source = configRepository.findById(configId).orElse(null);
        if (source != null) {
            configRepository.delete(source);
            return SourceConfigDTO.getInstance(source);
        }
        return null;
    }
}
