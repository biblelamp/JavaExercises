package tools;

/**
 * Test task Bookshelf from merck.com
 * Class Service provides some operation with the database
 *
 * @author Sergey Iryupin
 * @version dated Jun 02, 2018
 */

import controller.SQLite;
import model.Author;
import model.Book;
import org.hibernate.Session;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Service {

    /**
     * Filling the database tables from a file
     *
     * @param  {String} name of file
     * @return  void
     **/
    public void load(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        Session session = SQLite.getSession("create");
        session.beginTransaction();

        String tableName = null;
        for (String line : lines)
            if (!line.isEmpty())
                if (line.startsWith(";"))
                    tableName = line.substring(1);
                else {
                    String[] fields = line.split(",");
                    switch (tableName) {
                        case "Author":
                            session.save(new Author(fields[1]));
                            break;
                        case "Book":
                            session.save(new Book(fields[1], Integer.parseInt(fields[2])));
                            break;
                        case "Reader":
                            break;
                    }
                }
        session.getTransaction().commit();
        session.close();
    }
}