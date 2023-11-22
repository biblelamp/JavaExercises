package news.crawler.repository;

import news.crawler.domain.SourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceConfigRepository extends JpaRepository<SourceConfig, Integer> {
}
