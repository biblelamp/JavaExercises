/**
 * Java. Level 3. Lesson 1. Homework
 *
 * 1. Create a product table (id, title, cost) // -create
 * 2. Clear the table and fill it with 1000 products // -init
 * 3. Get the price of the product by name // -getprice <name>
 * 4. Change the price of product // -setprice <name> <price>
 * 5. List of products in the given price range // -list <price1> <price2>
 *
 * @author Sergey Iryupin
 * @version Feb 06, 2018
 */
import java.sql.DriverManager;
import java.sql.Statement;

public class HW2Lesson {

    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static final String DB_NAME = "jdbc:sqlite:goods.db";
    final String TABLE_NAME = "products";
    final String COL_ID = "id";
    final String COL_TITLE = "title";
    final String COL_PRICE = "price";
    final String SQL_CREATE_TABLE =
        "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
        "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            COL_TITLE + " TEXT," +
            COL_PRICE + " REAL" +
        ");";
    final String SQL_CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;

    final String CMD_CREATE = "-create";
    final String CMD_INIT = "-init";

    Statement stmt;

    public static void main(String[] args) {
        HW2Lesson hw = new HW2Lesson(DRIVER_NAME, DB_NAME);
        hw.createTable();
        hw.initTable(50);
    }

    HW2Lesson(String driverName, String dbName) { // get connection
        stmt = null;
        try {
            Class.forName(driverName);
            stmt = DriverManager.getConnection(dbName).createStatement();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void createTable() { // stage 1 - create table
        try {
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (Exception ex) { 
            System.out.println(ex.getMessage());
        }
    }

    void initTable(int quantity) { // stage 2 - init table
        try {
            stmt.executeUpdate(SQL_CLEAR_TABLE);
            for (int i = 1; i <= quantity; i++)
                stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (" + COL_TITLE + ", " + COL_PRICE + ") " +
                    "VALUES ('product" + i + "', '" + i*10 + "');");
        } catch (Exception ex) { 
            System.out.println(ex.getMessage());
        }
    }
    
    float getPriceByName(String name) { // stage 3 - get price by name
        return 0;
    }
}