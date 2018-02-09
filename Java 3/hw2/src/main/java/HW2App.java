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
 * @version Feb 09, 2018
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Creating a session
     * @return org.hibernate.Session
     */
    private Session createHibernateSession() {
        try {
            Map<String, String> settings = new HashMap<String, String>();
            settings.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
            settings.put("hibernate.connection.url", "jdbc:sqlite:goods.db");
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

    /**
     * Adding records to the table
     */
    private void recordsAdd() {
        try {
            System.out.println("Adding to the table of database:");
            Transaction tx = session.beginTransaction();
            for (int i = 1; i < 6; i++)
                session.save(new Product("product" + i, i*10));
            tx.commit();
            System.out.println("Records added.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reading records
     */
    private void recordsRead() {
        System.out.println("\nReading records from table:");
        String query = "select p from " + Product.class.getSimpleName() + " p";

        @SuppressWarnings("unchecked")
        List<Product> list = (List<Product>)session.createQuery(query).list();
        System.out.println(list);
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
     * Constructor
     */
    public HW2App() {
        session = createHibernateSession();
        if (session != null) {
            recordsAdd();
            //recordsRead();
            //recordFind(Integer.valueOf(Products[1][0]));
            if (session.isOpen())
                session.close();
        }
    }

    public static void main(String[] args) {
        new HW2App();
        System.exit(0);
    }
}