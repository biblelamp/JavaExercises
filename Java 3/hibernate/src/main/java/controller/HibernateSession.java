package controller;

/**
 * Java. Level 3. Lesson 2. Homework+
 * Creating hibernate session
 *
 * @author Sergey Iryupin
 * @version dated Jul 14, 2018
 */

import model.Product;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;

public class HibernateSession {
    private static Session session = null;

    private HibernateSession() {} // block of classical object creation

    /**
     * Creating a session
     * @param  driverName
     * @param  dbName
     * @param  mode [validate|update|create|create-drop]
     * @return void
     */
    private static void createHibernateSession(
            String driverName, String dbName, String mode) {
        try {
            Map<String, String> settings = new HashMap<String, String>();
            settings.put("hibernate.connection.driver_class", driverName);
            settings.put("hibernate.connection.url", dbName);
            settings.put("hibernate.connection.username", "");
            settings.put("hibernate.connection.password", "");
            settings.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", mode);

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(settings)
                    .build();

            session = new MetadataSources(registry)
                    .addAnnotatedClass(Product.class)
                    .getMetadataBuilder()
                    .build()
                    .getSessionFactoryBuilder()
                    .build()
                    .openSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }

    /**
     * To get connection like as the instance for the singleton
     *
     * @param {String} mode [validate|update|create|create-drop]
     * @return {org.hibernate.Session} session
     **/
    public static Session getSession(String driverName, String dbName, String mode) {
        if (session == null)
            createHibernateSession(driverName, dbName, mode);
        return session;
    }
}