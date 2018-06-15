/**
 * Test task Bookshelf from merck.com
 * Class for testing
 *
 * @author Sergey Iryupin
 * @version dated Jun 03, 2018
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BookShelfTest {
    private Service service;

    @Before
    @Test
    public void loadTest() {
        service = new Service();
        Assert.assertTrue(service.load(".\\..\\data.txt"));
    }

    @Test
    public void getAuthorsTest() {
        Assert.assertEquals(
                Arrays.asList("Mike McGrath", "Kathy Sierra", "Bruce Eckel",
                        "Herbert Schildt", "Cay Horstmann"),
                service.getAuthors());
    }

    @Test
    public void getBornByYearsTest() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2000,1);
        map.put(2001,1);
        map.put(2002,1);
        map.put(1998,1);
        map.put(1999,1);
        Assert.assertEquals(map, service.getBornByYears());
    }

    @Test
    public void getMostPopularAuthorsTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Herbert Schildt",5);
        map.put("Mike McGrath",3);
        map.put("Kathy Sierra",2);
        map.put("Bruce Eckel",1);
        Assert.assertEquals(map, service.getMostPopularAuthors());
    }
}