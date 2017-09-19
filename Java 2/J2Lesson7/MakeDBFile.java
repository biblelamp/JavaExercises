/**
 * Java. Level 2. Lesson 7
 * Making db file with users
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Jul 05, 2017
 */
import java.sql.*;

class MakeDBFile implements IConstants {

    final String NAME_TABLE = "users";
    final String SQL_CREATE_TABLE = "CREATE TABLE " + NAME_TABLE +
        "(login  CHAR(6) PRIMARY KEY NOT NULL," +
        " passwd CHAR(6) NOT NULL);";
    final String SQL_INSERT_MIKE = "INSERT INTO " + NAME_TABLE +
        " (login, passwd) " +
        "VALUES ('mike', 'qwerty');";
    final String SQL_INSERT_JONH = "INSERT INTO " + NAME_TABLE +
        " (login, passwd) " +
        "VALUES ('john', '12345');";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";

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
            connect = DriverManager.getConnection(SQLITE_DB);
        } catch (Exception e) { }

        // create table
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate(SQL_CREATE_TABLE);
        } catch (Exception e) { }

        // insert record(s)
        try {
            stmt.executeUpdate(SQL_INSERT_MIKE);
            stmt.executeUpdate(SQL_INSERT_JONH);
        } catch (Exception e) { }

        // print records
        try {
            rs = stmt.executeQuery(SQL_SELECT);
            System.out.println("LOGIN\tPASSWD");
            while (rs.next()) {
                System.out.println(rs.getString("login") + "\t" +
                    rs.getString(PASSWD_COL));
            }
        } catch (Exception e) { }
    }
}