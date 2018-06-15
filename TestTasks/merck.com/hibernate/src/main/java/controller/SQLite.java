package controller;

/**
 * Test task Bookshelf from merck.com
 * Class SQLite provides a hibernate session
 *
 * @author Sergey Iryupin
 * @version dated Jun 06, 2018
 */

import model.Author;
import model.Book;
import model.Reader;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;

public class SQLite {
    private static Session session = null;
    private final static String DRIVER = "org.sqlite.JDBC";
    private final static String PREFIX = "jdbc:sqlite:";
    private final static String DBNAME = "bookshelf.db";

    private SQLite() {} // block of classical object creation

    /**
     * Creating a session
     * @param  {String} driverName
     * @param  {String} dbName
     * @param  {String} mode [validate|update|create|create-drop]
     * @return void
     */
    private static void createHibernateSession(
            String driverName, String dbName, String mode) {
        try {
            Map<String, String> settings = new HashMap<>();
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
                    .addAnnotatedClass(Author.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Reader.class)
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
    public static Session getSession(String mode) {
        if (session == null)
            createHibernateSession(DRIVER, PREFIX + DBNAME, mode);
        return session;
    }

    /**
     * To close connection if it's open
     *
     * @return {void}
     */
    public static void close() {
        if (session != null)
            session.close();
    }
}