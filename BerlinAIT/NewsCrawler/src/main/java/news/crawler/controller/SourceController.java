package news.crawler.controller;

import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.service.SourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/source")
public class SourceController {

    @Autowired
    private SourceConfigService configService;

    @GetMapping("/all")
    public List<SourceConfigDTO> findAll() {
        return configService.findAll();
    }

    @PostMapping("/add")
    public SourceConfigDTO add(@RequestBody SourceConfigDTO config) {
        return configService.add(config);
    }

    @PutMapping("/update")
    public SourceConfigDTO update(@RequestBody SourceConfigDTO config) {
        return configService.update(config);
    }

    @DeleteMapping("/delete/{id}")
    public SourceConfigDTO delete(@PathVariable Integer id) {
        return configService.delete(id);
    }
}
