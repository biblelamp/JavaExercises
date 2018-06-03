package tools;

/**
 * Test task Bookshelf from merck.com
 * Class Service provides some operation with the database
 *
 * @author Sergey Iryupin
 * @version dated Jun 03, 2018
 */

import controller.SQLite;
import model.Author;
import model.Book;
import model.ITable;
import model.Reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;

public class Service {

    /**
     * Filling the database tables from a file
     *
     * @param  {String} name of file
     * @return {boolean}
     **/
    public boolean load(String filename) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filename),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
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
         return true;
    }

    /**
     * All authors that wrote at least 1 book
     *
     * @return {List<String>} list of authors
     */
    public List<String> getAuthors() {
        List<String> list = new ArrayList<>();
        try (ResultSet rs = SQLite.getConnection()
                .createStatement()
                .executeQuery(
                     "SELECT distinct authors.name FROM " +
                             "Author AS authors, Book AS books " +
                             "WHERE authors.ID = books.AUTHORID;")) {
            while (rs.next())
                list.add(rs.getString("name")) ;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
        //return new Book().getAuthors();
    }

    /**
     * Getting number of readers born in each year
     *
     * @return {Map<Integer, Integer>} botn by years
     */
    public Map<Integer, Integer> getBornByYears() {
        return new Reader().getBornByYears();
    }

    /**
     * Getting list of authors with their popularity
     *
     * @return Map<String, Integer> list of authors
     */
    public Map<String, Integer> getMostPopularAuthors() {
        return new Reader().getMostPopularAuthors();
    }

    /**
     * Sorting map by values
     *
     * @param {Map<K, V>} unsorted map
     * @return {Map<K, V>} sorted map
     */
    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0) return 1;
                else return compare;
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}