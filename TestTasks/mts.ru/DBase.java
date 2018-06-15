/**
 * Class DBase
 * The class provides work with the SQLite database:
 *  - table PRODUCT stores information about products
 *  - table LOG stores requests history
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 09, 2017
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

class DBase {
    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static final String DB_FILE = "product.db";
    static Connection connect = null;

    public static void openDB() { // open and init DB if it's needed
        try {
            if (connect == null) {
                // open db
                Class.forName(DRIVER_NAME);
                connect = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
                // create tables
                Statement stmt = connect.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS product" +
                    "(name   CHAR(12) PRIMARY KEY NOT NULL," +
                    " price  INT NOT NULL," +
                    " amount INT," +
                    " sales  INT," +  // sold quantity
                    " batch  INT," +  // batch size for price change
                    " change INT);"); // price change
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS log" +
                    "(date   TEXT NOT NULL," +
                    " name   CHAR(12)," +
                    " action TEXT," +
                    " result CHAR(8));");
                // add two records if table is empty
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM product;");
                if (rs.getInt(1) == 0) {
                    stmt.executeUpdate("INSERT INTO product" +
                        "(name, price, amount, batch, change) " +
                        "VALUES ('apple', 15, 1000, 100, -1);");
                    stmt.executeUpdate("INSERT INTO product" +
                        "(name, price, amount, batch, change) " +
                        "VALUES ('wine', 250, 50, 20, 10);");
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getIntField(String name, String field) { // get int field
        Integer result = null;
        openDB();
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM product WHERE name='" + name + "';");
            while (rs.next())
                result = rs.getInt(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setIntField(String name, String field, int value) { // set int field
        openDB();
        try (Statement stmt = connect.createStatement()) {
                stmt.executeUpdate(
                    "UPDATE product SET " + field + "=" + value +
                    " WHERE name='" + name + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLog(String name, String action, String result) {
        openDB();
        try (Statement stmt = connect.createStatement()) {
            stmt.executeUpdate("INSERT INTO log" +
                "(date, name, action, result) " +
                "VALUES ('" + (new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss:SSS").format(new Date())) + "', '" +
                name + "', '" + action + "', '" + result + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}