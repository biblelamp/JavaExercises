package news.crawler.repository;

import news.crawler.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    boolean existsByNewsUrl(String newsUrl);

    @Query("SELECT e FROM Event e ORDER BY e.dateTime DESC")
    Page<Event> findByPage(Pageable pageable);
}
