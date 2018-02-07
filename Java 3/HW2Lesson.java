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
 * @version Feb 07, 2018
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
    final String SQL_CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;
    final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME + " WHERE title=?";
    final String SQL_UPDATE =
        "UPDATE " + TABLE_NAME + " SET price=? WHERE title=?";
    final String SQL_LIST_IN_RANGE =
        "SELECT * FROM " + TABLE_NAME + " WHERE price>=? AND price<=?";

    final String CMD_CREATE = "-create";
    final String CMD_INIT = "-init";

    Connection connect;
    Statement stmt;

    public static void main(String[] args) {
        HW2Lesson hw = new HW2Lesson(DRIVER_NAME, DB_NAME);
        //hw.createTable();
        //hw.initTable(50);
        //System.out.println(hw.getPriceByName("product10"));
        //hw.setPriceByName("product10", 105.5f);
        //System.out.println(hw.getPriceByName("product10"));
        List<String> list = hw.getListInRange(100, 500);
        for (String item : list)
            System.out.println(item);
    }

    HW2Lesson(String driverName, String dbName) { // get connection
        stmt = null;
        try {
            Class.forName(driverName);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    void createTable() { // stage 1. create table
        try {
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void initTable(int quantity) { // stage 2. init table
        try {
            stmt.executeUpdate(SQL_CLEAR_TABLE);
            for (int i = 1; i <= quantity; i++)
                stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (" + COL_TITLE + ", " + COL_PRICE + ") " +
                    "VALUES ('product" + i + "', '" + i*10 + "');");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    float getPriceByName(String name) { // stage 3. get price by name
        float price = -1;
        try {
            PreparedStatement pstmt = connect.prepareStatement(SQL_SELECT);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                price = rs.getFloat(COL_PRICE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return price;
    }

    void setPriceByName(String name, float price) { // stage 4. set price by name
        try {
            PreparedStatement pstmt = connect.prepareStatement(SQL_UPDATE);
            pstmt.setFloat(1, price);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    List<String> getListInRange(float priceFrom, float priceTo) { // stage 5. list in range
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connect.prepareStatement(SQL_LIST_IN_RANGE);
            pstmt.setFloat(1, priceFrom);
            pstmt.setFloat(2, priceTo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                list.add(
                    rs.getInt(COL_ID) + "\t" +
                    rs.getString(COL_TITLE) + "\t" +
                    rs.getFloat(COL_PRICE));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}