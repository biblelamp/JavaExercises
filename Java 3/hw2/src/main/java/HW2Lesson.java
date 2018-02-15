/**
 * Java. Level 3. Lesson 2. Homework
 *
 * 1. Create a product table (id, title, cost) // -create
 * 2. Clear the table and fill it with 1000 products // -init <quantity>
 * 3. Get the price of the product by name // -getprice <name>
 * 4. Change the price of product // -setprice <name> <price>
 * 5. List of products in the given price range // -list <price1> <price2>
 * 6. Run points 1..5 using hibernate
 *
 * @author Sergey Iryupin
 * @version Feb 15, 2018
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import model.Product;

public class HW2Lesson {
    Session session = null;

    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String DB_NAME = "jdbc:sqlite:products.db";
    final String PRODUCT_NAME = "product";

    static final String CMD_CREATE = "-create";
    static final String CMD_INIT = "-init";
    static final String CMD_GETPRICE = "-getprice";
    static final String CMD_SETPRICE = "-setprice";
    static final String CMD_LIST = "-list";
    static final String MSG_NOTFOUND = "Not found";

    public static void main(String[] args) {
        HW2Lesson hw = new HW2Lesson();
        if (args.length > 0)
            switch (args[0]) {
                case CMD_CREATE:
                    hw.createTable();
                    break;
                case CMD_INIT:
                    if (args.length > 1)
                        hw.initTable(Integer.parseInt(args[1]));
                    break;
                case CMD_GETPRICE:
                    if (args.length > 1) {
                        float price = hw.getPriceByName(args[1]);
                        System.out.println((price < 0)? MSG_NOTFOUND : price);
                    }
                    break;
                case CMD_SETPRICE:
                    if (args.length > 2)
                        hw.setPriceByName(args[1], Float.parseFloat(args[2]));
                    break;
                case CMD_LIST:
                    if (args.length > 2) {
                        for (String item : hw.getListInRange(
                                Float.parseFloat(args[1]),
                                Float.parseFloat(args[2])))
                            System.out.println(item);
                    }
            }
        System.exit(0);
    }

    /**
     * Stage 1. create table
     */
    void createTable() {
        Session session = createHibernateSession(DRIVER_NAME, DB_NAME, "create");
        session.close();
    }

    /**
     * Stage 2. init table
     */
    void initTable(int quantity) {
        Session session = createHibernateSession(DRIVER_NAME, DB_NAME, "create");
        session.beginTransaction();

        for (int i = 1; i <= quantity; i++)
            session.save(new Product(PRODUCT_NAME + i, i*10));

        session.getTransaction().commit();
        session.close();
    }

    /**
     * stage 3. get price by name
     */
    float getPriceByName(String title) {
        float price = -1;
        Session session = createHibernateSession(DRIVER_NAME, DB_NAME, "validate");

        Query query = session.createQuery("from " + 
            Product.class.getSimpleName() + " where title=:title")
                .setParameter("title", title);
        List<Product> result = (List<Product>)query.getResultList();
        if (result.size() > 0)
            price = result.get(0).getPrice();

        session.close();
        return price;
    }

    /**
     * stage 4. set price by name
     */
    void setPriceByName(String title, float price) {
        Session session = createHibernateSession(DRIVER_NAME, DB_NAME, "validate");
        session.beginTransaction();

        Query query = session.createQuery("update " +
            Product.class.getSimpleName() +
            " set price=:price where title=:title")
                .setParameter("price", price)
                .setParameter("title", title);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    /**
     * stage 5. list of products in price range
     */
    List<String> getListInRange(float priceFrom, float priceTo) {
        List<String> list = new ArrayList<>();
        Session session = createHibernateSession(DRIVER_NAME, DB_NAME, "validate");

        Query query = session.createQuery("from " +
            Product.class.getSimpleName() +
            " where price between :priceFrom and :priceTo")
                .setParameter("priceFrom", priceFrom)
                .setParameter("priceTo", priceTo);
        List<Product> products = query.getResultList();

        session.close();
        for (Product product : products)
            list.add(product.getId() + "\t" + product.getTitle() +
                "\t" + product.getPrice());
        return list;
    }

    /**
     * Creating a session
     * @param  driverName
     * @param  dbName
     * @param  mode [validate|update|create|create-drop]
]    * @return org.hibernate.Session
     */
    private Session createHibernateSession(String driverName, String dbName,
            String mode) {
        Session session = null;
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
        return session;
    }
}