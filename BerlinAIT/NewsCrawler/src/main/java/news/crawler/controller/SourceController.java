package news.crawler.controller;

import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.service.SourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<SourceConfigDTO> update(@RequestBody SourceConfigDTO config) {
        SourceConfigDTO responce = configService.update(config);
        if (responce == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responce);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SourceConfigDTO> delete(@PathVariable Integer id) {
        SourceConfigDTO responce = configService.delete(id);
        if (responce == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responce);
    }
}
