package lesson9.spring;

import lesson9.Event;
import lesson9.EventDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class EventDaoSpringDemo {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EventDAO eventDAO = context.getBean(EventDAO.class);
        Event event = eventDAO.findById(1);
        System.out.println(event);
    }
}
