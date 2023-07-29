import com.google.common.cache.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaLiveTimeExpire {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Map<String, String> sourceMap = new HashMap<>();
        sourceMap.put("1st", "first");
        sourceMap.put("2nd", "second");
        sourceMap.put("3rd", "third");

        CacheLoader<String, String> loader = new CacheLoader<>() {
            @Override
            public String load(String key) {
                System.out.println("add item, key: " + key);
                return sourceMap.get(key);
            }
        };

        RemovalListener<String, String> removalListener = new RemovalListener<>() {
            public void onRemoval(RemovalNotification<String, String> removal) {
                System.out.println("Remove item, key: " + removal.getKey());
                sourceMap.remove(removal.getKey());
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MILLISECONDS)
                .removalListener(removalListener)
                .build(loader);

        System.out.println(System.currentTimeMillis() + ": " + cache.asMap());
        cache.getUnchecked("1st");
        System.out.println(System.currentTimeMillis() + ": " + cache.asMap());
        Thread.sleep(2);
        cache.getUnchecked("2nd");
        System.out.println(System.currentTimeMillis() + ": " + cache.asMap());
        Thread.sleep(2);
        cache.getUnchecked("3rd");
        System.out.println(System.currentTimeMillis() + ": " + cache.asMap());
        for (int i = 0; i < 6; i++) {
            Thread.sleep(2);
            System.out.println(System.currentTimeMillis() + ": " + cache.asMap());
        }
        // obsolete records will be deleted
        cache.cleanUp();
        System.out.println(sourceMap.get("1st"));
    }
}
