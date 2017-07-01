/**
 * Java. Level 2. Lesson 7
 * Making db file with users
 *
 * @author Sergey Iryupin
 * @version 0.1.1 dated Jul 01, 2017
 */
import java.sql.*;

class MakeDBFile {

    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String PREFIX_NAME = "jdbc:sqlite:";
    final String NAME_DB = "chat.db";
    final String NAME_TABLE = "users";

    Connection connect;
    Statement stmt;
    ResultSet rs;
    String sql;

    public static void main(String[] args) {
        new MakeDBFile();
    }

    MakeDBFile() {
        // open db file
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(PREFIX_NAME + NAME_DB);
        } catch (Exception e) { }

        // create table
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("CREATE TABLE " + NAME_TABLE +
                "(login  CHAR(6) PRIMARY KEY NOT NULL," +
                " passwd CHAR(6) NOT NULL);");
        } catch (Exception e) { }

        // insert record(s)
        try {
            stmt.executeUpdate("INSERT INTO " + NAME_TABLE +
                " (login, passwd) " +
                "VALUES ('mike', 'qwerty');");
            stmt.executeUpdate("INSERT INTO " + NAME_TABLE +
                " (login, passwd) " +
                "VALUES ('john', '12345');");
        } catch (Exception e) { }

        // print records
        try {
            rs = stmt.executeQuery("SELECT * FROM " + NAME_TABLE + ";" );
            System.out.println("LOGIN\tPASSWD");
            while (rs.next()) {
                System.out.println(rs.getString("login") + "\t" +
                    rs.getString("passwd"));
            }
        } catch (Exception e) { }
    }
}