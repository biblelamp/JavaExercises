/**
 * Java. Level 2. Lesson 7
 * Making SQLite db file with users
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Jan 15, 2018
 */
import java.sql.*;

class MakeDBFile implements IConstants {

    final String NAME_TABLE = "users";
    final String SQL_CREATE_TABLE =
        "DROP TABLE IF EXISTS " + NAME_TABLE + ";" +
        "CREATE TABLE " + NAME_TABLE +
        "(login  CHAR(6) PRIMARY KEY NOT NULL," +
        " passwd CHAR(6) NOT NULL);";
    final String SQL_INSERT_MIKE =
        "INSERT INTO " + NAME_TABLE +
        " (login, passwd) " +
        "VALUES ('mike', 'qwe');";
    final String SQL_INSERT_JONH =
        "INSERT INTO " + NAME_TABLE +
        " (login, passwd) " +
        "VALUES ('john', 'rty');";
    final String SQL_SELECT = "SELECT * FROM " + NAME_TABLE + ";";

    public static void main(String[] args) {
        new MakeDBFile();
    }

    MakeDBFile() {
        try {
            // loads a class, including running its static initializers
            Class.forName(DRIVER_NAME);
            // attempts to establish a connection to the given database URL
            Connection connect = DriverManager.getConnection(SQLITE_DB);
            // сreates an object for sending SQL statements to the database
            Statement stmt = connect.createStatement();

            // create table
            stmt.executeUpdate(SQL_CREATE_TABLE);

            // insert record(s)
            stmt.executeUpdate(SQL_INSERT_MIKE);
            stmt.executeUpdate(SQL_INSERT_JONH);

            // print records
            ResultSet rs = stmt.executeQuery(SQL_SELECT);
            System.out.println("LOGIN\tPASSWD");
            while (rs.next())
                System.out.println(
                    rs.getString("login") + "\t" +
                    rs.getString(PASSWD_COL));
            rs.close();
            stmt.close();
            connect.close();
        } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
        }
    }
}