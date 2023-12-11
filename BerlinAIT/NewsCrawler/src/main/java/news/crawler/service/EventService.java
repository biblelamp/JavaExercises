package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.EventRepository;
import news.crawler.service.executor.CrawlerExecutor;
import news.crawler.service.executor.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * News filtering, on the basis of which news are not in db
     * @param events
     * @return list of news
     */
    public List<EventDTO> filterNewNews(List<EventDTO> events) {
        List<EventDTO> newEvents = new ArrayList<>(events.size());
        events.forEach(event -> {
            if (!eventRepository.existsByNewsUrl(event.getNewsUrl())) {
                newEvents.add(event);
            }
        });
        return newEvents;
    }

    /**
     * Saving read news in the database
     * @param events
     * @param config
     */
    public void save(List<EventDTO> events, SourceConfig config) {
        List<Event> newEvents = new ArrayList<>(events.size());
        for (EventDTO e : events) {
            Event event = new Event();
            event.setSourceConfig(config);
            event.setTitle(e.getTitle());
            event.setNewsUrl(e.getNewsUrl());
            event.setImageUrl(e.getImageUrl());
            event.setText(e.getText());
            event.setDateTime(e.getDateTime());
            newEvents.add(event);
        }
        eventRepository.saveAll(newEvents);
        log.info("Succesfully saved {} events .", newEvents.size());
    }

    public List<EventDTO> findByPage(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Event> events = eventRepository.findByPage(pageable);
        List<EventDTO> result = new ArrayList<>(events.getSize());

        events.forEach(i -> result.add(EventDTO.getInstance(i)));
        return result;
    }

    public List<EventDTO> checkParser(SourceConfigDTO config) throws Exception {
        return checkParser(config.getRootUrl(), config.getNewsSuffix(), config.getClassName());
    }

    public List<EventDTO> checkParser(String rootUrl, String newsSuffix, String className) throws Exception {
        Class<?> cls = Class.forName(CrawlerExecutor.PACKAGE + className);
        Constructor<?> constructor = cls.getConstructor();
        Execute execClass = (Execute) constructor.newInstance();

        List<EventDTO> events = execClass.getNewsTitles(new SourceConfig("https://" + rootUrl, "/" + newsSuffix + "/"));
        return execClass.getNews(events);
    }
}
