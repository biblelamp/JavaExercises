/**
 * Java. Level 3. Lesson 2. Homework
 *
 * 1. Create a product table (id, title, cost) // -create
 * 2. Clear the table and fill it with 1000 products // -init
 * 3. Get the price of the product by name // -getprice <name>
 * 4. Change the price of product // -setprice <name> <price>
 * 5. List of products in the given price range // -list <price1> <price2>
 * 6. Run points 1..5 using hibernate
 *
 * @author Sergey Iryupin
 * @version Feb 10, 2018
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.Product;

public class HW2App {
    Session session = null;

    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String DB_NAME = "jdbc:sqlite:goods.db";

    public static void main(String[] args) {
        new HW2App();
        System.exit(0);
    }

    public HW2App() {
        session = createHibernateSession(DRIVER_NAME, DB_NAME);
        if (session != null) {
            initTable(10);
            System.out.println(getPriceByName("product5"));
            if (session.isOpen())
                session.close();
        }
    }

    /**
     * Stage 2. init table
     */
    void initTable(int quantity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 1; i <= quantity; i++)
            session.save(new Product("product" + i, i*10));
        session.getTransaction().commit();
        session.close();
    }

    /**
     * stage 3. get price by name
     */
    float getPriceByName(String title) {
        float price = -1;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM " +
            Product.class.getSimpleName() + " WHERE title = :title")
                .setParameter("title", title);
        List<Product> result = query.getResultList();
        if (result.size() > 0)
            price = result.get(0).getPrice();
        session.getTransaction().commit();
        session.close();
        return price;
    }

    /**
     * Seeking record
     */
    private void recordFind(final int id) {
        System.out.println("\nReading record by ID:");
        Product Product = (Product) session.load(Product.class, id);
        System.out.println(Product);
    }

    /**
     * Creating a session
     * @param  String driverName
     * @param  String dbName
     * @return org.hibernate.Session
     */
    private Session createHibernateSession(String driverName, String dbName) {
        try {
            Map<String, String> settings = new HashMap<String, String>();
            settings.put("hibernate.connection.driver_class", driverName);
            settings.put("hibernate.connection.url", dbName);
            settings.put("hibernate.connection.username", "");
            settings.put("hibernate.connection.password", "");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "update");

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(settings)
                    .build();

            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(Product.class);

            Metadata metadata = sources
                    .getMetadataBuilder()
                    .build();

            SessionFactory sessionFactory = metadata
                    .getSessionFactoryBuilder()
                    .build();

            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println("Session created successfully.");
        return session;
    }
}