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
import model.Reader;
import org.hibernate.Session;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public class Service {

    /**
     * Filling the database tables from a file
     *
     * @param  {String} name of file
     * @return {boolean}
     **/
    public boolean load(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
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
                            session.save(new Book(Integer.parseInt(fields[0]),
                                    fields[1], Integer.parseInt(fields[2])));
                            break;
                        case "Reader":
                            session.save(new Reader(Integer.parseInt(fields[0]),
                                    fields[1], fields[2], fields[3]));
                            break;
                    }
                }
        session.getTransaction().commit();
        session.close();
        return true;
    }

    /**
     * All authors that wrote at least 1 book
     *
     * @return {List<String>} list of authors
     */
    public List<String> getAuthors() {
        List<String> list;
        list = SQLite.getSession("validate").createQuery(
                "SELECT distinct authors.name FROM " +
                "Author AS authors, Book AS books " +
                "WHERE authors.id = books.authorid")
            .getResultList();
        return list;
    }

    /**
     * Getting number of readers born in each year
     *
     * @return {Map<Integer, Integer>} botn by years
     */
    public Map<Integer, Integer> getBornByYears() {
        Map<Integer, Integer> map = new HashMap<>();
        List<String> dates = SQLite.getSession("validate").createQuery(
                "SELECT dateofbirth FROM Reader")
                .getResultList();
        for (String date : dates) {
            int year = Integer.parseInt(date.substring(0, 4));
            map.put(year, map.getOrDefault(year, 0) + 1);
        }
        return map;
    }

    /**
     * Getting list of authors with their popularity
     *
     * @return Map<String, Integer> list of authors
     */
    public Map<String, Integer> getMostPopularAuthors() {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = SQLite.getSession("validate").createQuery(
                "SELECT books FROM Reader")
                .getResultList();
        for (String item : list) {
            String[] books = item.split(" ");
            for (String id : books) {
                List<String> name = SQLite.getSession("validate").createQuery(
                        "SELECT authors.name FROM " +
                         "Author AS authors, Book AS books " +
                         "WHERE authors.id = books.authorid and books.id = :id")
                        .setParameter("id", Integer.parseInt(id))
                        .getResultList();
                map.put(name.get(0), map.getOrDefault(name.get(0), 0) + 1);
            }
        }
        return map;
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