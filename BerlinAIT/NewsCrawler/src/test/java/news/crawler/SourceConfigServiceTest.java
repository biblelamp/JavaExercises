package news.crawler;

import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.service.SourceConfigService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SourceConfigServiceTest {

    @Autowired
    private SourceConfigService configService;

    @Test
    @Order(1)
    public void testAdd() {
        SourceConfigDTO sourceCFG = new SourceConfigDTO(null, "root", "news", "className", null);
        SourceConfigDTO config = configService.add(sourceCFG);
        Assertions.assertNotNull(config.getId());

        List<SourceConfigDTO> configs = configService.findAll();
        Assertions.assertEquals(1, configs.size());

        SourceConfigDTO cfg = configs.get(0);
        Assertions.assertEquals(config.getId(), cfg.getId());
        Assertions.assertEquals(config.getRootUrl(), cfg.getRootUrl());
        Assertions.assertEquals(config.getNewsSuffix(), cfg.getNewsSuffix());
        Assertions.assertEquals(config.getClassName(), cfg.getClassName());
    }

    @Test
    @Order(2)
    public void testUpdate() {
        SourceConfigDTO sourceCFG = new SourceConfigDTO(1, "rootNew", "news", "className", null);
        SourceConfigDTO config = configService.update(sourceCFG);
        Assertions.assertNotNull(config);
        Assertions.assertEquals(sourceCFG.getRootUrl(), config.getRootUrl());

        List<SourceConfigDTO> configs = configService.findAll();
        Assertions.assertEquals(1, configs.size());
    }

    @Test
    @Order(3)
    public void testDelete() {
        SourceConfigDTO config = configService.delete(1);
        Assertions.assertNotNull(config);

        List<SourceConfigDTO> configs = configService.findAll();
        Assertions.assertEquals(0, configs.size());
    }
}
