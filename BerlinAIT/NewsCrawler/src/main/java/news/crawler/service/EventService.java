package news.crawler.service;

import lombok.extern.slf4j.Slf4j;
import news.crawler.controller.dto.EventDTO;
import news.crawler.controller.dto.EventShortDTO;
import news.crawler.controller.dto.SourceConfigDTO;
import news.crawler.domain.Event;
import news.crawler.domain.SourceConfig;
import news.crawler.repository.EventRepository;
import news.crawler.service.executor.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void save(List<EventDTO> events, SourceConfig config) {
        List<Event> eventList = new ArrayList<>(events.size());
        for (EventDTO e : events) {
            if (!eventRepository.existsByNewsUrl(e.getNewsUrl())) {
                Event event = new Event();
                event.setSourceConfig(config);
                event.setTitle(e.getTitle());
                event.setNewsUrl(e.getNewsUrl());
                event.setImageUrl(e.getImageUrl());
                event.setText(e.getText());
                event.setDateTime(e.getDateTime());
                eventList.add(event);
            }
        }
        eventRepository.saveAll(eventList);
        log.info("{} events saved succesfully.", eventList.size());
    }

    public List<EventShortDTO> findAll() {
        List<Event> events = eventRepository.findAll(Sort.by("dateTime").descending());
        List<EventShortDTO> result = new ArrayList<>(events.size());
        events.forEach(i -> result.add(EventShortDTO.getInstance(i)));
        return result;
    }

    public List<EventDTO> parseTest(SourceConfigDTO config) throws Exception {
        return parseTest(config.getRootUrl(), config.getNewsSuffix(), config.getClassName());
    }

    public List<EventDTO> parseTest(String rootUrl, String newsSuffix, String className) throws Exception {
        Class<?> cls = Class.forName("news.crawler.service.executor." + className);
        Constructor<?> constructor = cls.getConstructor();
        Execute execClass = (Execute) constructor.newInstance();

        return execClass.execute(new SourceConfig("https://" + rootUrl, "/" + newsSuffix + "/"));
    }
}
