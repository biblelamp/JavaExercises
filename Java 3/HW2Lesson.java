/**
 * Java. Level 3. Lesson 2. Homework
 *
 * 1. Create a product table (id, title, cost) // -create
 * 2. Clear the table and fill it with 1000 products // -init <quantity>
 * 3. Get the price of the product by name // -getprice <name>
 * 4. Change the price of product // -setprice <name> <price>
 * 5. List of products in the given price range // -list <price1> <price2>
 *
 * @author Sergey Iryupin
 * @version Jul 14, 2018
 * @link https://github.com/biblelamp
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
    final String SQL_CLEAR_TABLE =
        "DELETE FROM " + TABLE_NAME + ";" +
        "DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'"; // reset
    final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +
        " (" + COL_TITLE + ", " + COL_PRICE + ") VALUES (?, ?);";
    final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME + " WHERE title=?;";
    final String SQL_UPDATE =
        "UPDATE " + TABLE_NAME + " SET price=? WHERE title=?;";
    final String SQL_LIST_IN_RANGE =
        "SELECT * FROM " + TABLE_NAME + " WHERE price>=? AND price<=?";

    static final String CMD_CREATE = "-create";
    static final String CMD_INIT = "-init";
    static final String CMD_GETPRICE = "-getprice";
    static final String CMD_SETPRICE = "-setprice";
    static final String CMD_LIST = "-list";
    static final String MSG_NOTFOUND = "Not found";

    Connection connection = null;

    public static void main(String[] args) {
        HW2Lesson hw = new HW2Lesson(DRIVER_NAME, DB_NAME);
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
        hw.close();
    }

    HW2Lesson(String driverName, String dbName) { // open connection
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(dbName);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    void close() {                                // close connection
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    void createTable() {                          // stage 1. create table
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void initTable(int quantity) {                // stage 2. init & fill table
        try (Statement stmt = connection.createStatement();
                PreparedStatement pstmt =
                    connection.prepareStatement(SQL_INSERT)) {
            stmt.executeUpdate(SQL_CLEAR_TABLE);
            for (int i = 1; i <= quantity; i++) {
                pstmt.setString(1, "product" + i);
                pstmt.setFloat(2, i*10);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    float getPriceByName(String name) {           // stage 3. get price by name
        float price = -1;
        try (PreparedStatement pstmt =
                connection.prepareStatement(SQL_SELECT)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                price = rs.getFloat(COL_PRICE);
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }
                                                  // stage 4. set price by name
    void setPriceByName(String name, float price) {
        try (PreparedStatement pstmt =
                connection.prepareStatement(SQL_UPDATE)) {
            pstmt.setFloat(1, price);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
                                                  // stage 5. list in range
    List<String> getListInRange(float priceFrom, float priceTo) {
        List<String> list = new ArrayList<>();
        try (PreparedStatement pstmt =
                connection.prepareStatement(SQL_LIST_IN_RANGE)) {
            pstmt.setFloat(1, priceFrom);
            pstmt.setFloat(2, priceTo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                list.add(rs.getInt(COL_ID) + "\t" +
                    rs.getString(COL_TITLE) + "\t" +
                    rs.getFloat(COL_PRICE));
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}