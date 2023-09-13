package hibernate;

import hibernate.domain.Event;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerDemo {

    static EntityManager em;
    public static void main(String[] args) {
        // Получаем фабрику менеджеров сущностей
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        // Из фабрики создаем EntityManager
        em = factory.createEntityManager();

        Event event = findById(1);
        System.out.println(event);

        //Event newEvent = add(new Event("Rock Concert", "Berlin"));
        event = findById(3);
        System.out.println(event);

        //event.setCity("Prague");
        //event = update(event);
        //System.out.println(event);

        event.setName(null);
        event.setCity(null);
        delete(event);
    }

    static Event findById(int id) {
        return em.find(Event.class, id);
    }

    static Event add(Event event) {
        // Открываем транзакцию
        em.getTransaction().begin();
        // Create (сохраняем в базе данных, и благодаря этому сущность
        // становится управляемой Hibernate и заносится в контекст постоянства)
        em.persist(event);
        // Подтверждаем транзакцию
        em.getTransaction().commit();
        return event;
    }

    static Event update(Event event) {
        em.getTransaction().begin();
        em.merge(event);
        em.getTransaction().commit();
        return event;
    }

    static void delete(Event event) {
        em.getTransaction().begin();
        em.remove(event);
        em.getTransaction().commit();
    }
}
