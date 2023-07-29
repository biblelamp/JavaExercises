import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * from https://www.tutorialspoint.com/guava/guava_caching_utilities.htm
 */
public class GuavaCachingUtils {
    public static void main(String[] args) throws ExecutionException {

        // create a cache for employees based on their employee id
        LoadingCache<Integer, Employee> employeeCache =
                CacheBuilder.newBuilder()
                        .maximumSize(100)                              // maximum 100 records can be cached
                        .expireAfterAccess(30, TimeUnit.MINUTES)       // cache will expire after 30 minutes of access
                        .build(new CacheLoader<Integer, Employee>() {  // build the cacheloader
                            @Override
                            public Employee load(Integer id) throws Exception {
                                //make the expensive call
                                return getFromDatabase(id);
                            }
                        });

        // on first invocation, cache will be populated with corresponding
        // employee record
        System.out.println("Invocation #1");
        System.out.println(employeeCache.get(100));
        System.out.println(employeeCache.get(103));
        System.out.println(employeeCache.get(110));

        // second invocation, data will be returned from cache
        System.out.println("Invocation #2");
        System.out.println(employeeCache.get(100));
        System.out.println(employeeCache.get(103));
        System.out.println(employeeCache.get(110));
    }

    private static Employee getFromDatabase(Integer id) {

        Employee e1 = new Employee(100, "Finance", "Mahesh");
        Employee e2 = new Employee(103, "IT", "Rohan");
        Employee e3 = new Employee(110, "Admin", "Sohan");

        Map<Integer, Employee> database = new HashMap<Integer, Employee>();

        database.put(100, e1);
        database.put(103, e2);
        database.put(110, e3);

        System.out.println("Database hit for id=" + id);

        return database.get(id);
    }
}
