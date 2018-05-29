package tools;

/**
 * Test task Bookshelf from merck.com
 * Class Service provides some operation with the database
 *
 * @author Sergey Iryupin
 * @version dated May 28..29, 2018
 */

import controller.SQLite;
import model.Author;
import model.Book;
import model.ITable;
import model.Reader;
import org.sqlite.SQLiteConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Connection connection = SQLite.getConnection();
        Map<String, ITable> map = new HashMap<>();
        map.put(Author.class.getSimpleName(), new Author().dropTable(connection));
        map.put(Book.class.getSimpleName(), new Book().dropTable(connection));
        map.put(Reader.class.getSimpleName(), new Reader().dropTable(connection));
        String tableName = null;
        for (String line : lines)
            if (!line.isEmpty())
                if (line.startsWith(";"))
                    tableName = line.substring(1);
                else
                    map.get(tableName).addLine(line);
    }
}