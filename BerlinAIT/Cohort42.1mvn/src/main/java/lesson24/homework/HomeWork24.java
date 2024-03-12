package lesson24.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #24 ext
 *
 * @author Sergey Iryupin
 * @version 11-Mar-24
 */
public class HomeWork24 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/temp/sqlite_db");

        StudentDAO studentDAO = new StudentDAO(connection);
        // add student
        //studentDAO.add(new Student("Peter", "cohort43"));
        // update
        studentDAO.update(new Student(8, null, "cohort43.1"));
        // delete
        studentDAO.delete(8);
        // read all students
        List<Student> students = studentDAO.readAll();
        for (Student student : students) {
            System.out.println(student);
        }

        studentDAO.close();
        connection.close();
    }
}
