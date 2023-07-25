package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
