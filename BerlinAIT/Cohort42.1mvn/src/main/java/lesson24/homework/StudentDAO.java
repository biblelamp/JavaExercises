package lesson24.homework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Statement stmt;

    public StudentDAO(Connection connection) throws SQLException {
        stmt = connection.createStatement();
    }

    public void close() throws SQLException {
        stmt.close();
    }

    public void add(Student student) throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, group_name) VALUES ('" +
                student.getName() + "', '" + student.getGroupName() + "')");
    }

    public List<Student> readAll() throws SQLException {
        List<Student> result = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
        while (rs.next()) {
            result.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("group_name")));
        }
        return result;
    }

    public void update(Student student) throws SQLException {
        stmt.executeUpdate("UPDATE students SET group_name = '" + student.getGroupName() +
                "' WHERE id = " + student.getId());
    }

    public void delete(Integer id) throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE id = " + id);
    }
}
