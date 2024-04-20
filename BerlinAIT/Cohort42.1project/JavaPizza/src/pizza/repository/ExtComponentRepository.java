package pizza.repository;

import pizza.data.ExtСomponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExtComponent repository
 * Implementation of access methods to the ExtComponent data source
 *
 * @author Sergey Iryupin
 * @version 19-Apr-24
 */
public class ExtComponentRepository implements CrudRepository<Integer, ExtСomponent> {
    private Map<Integer, ExtСomponent> componentMap;

    public ExtComponentRepository() {
        this.componentMap = new HashMap<>();
    }

    @Override
    public void put(ExtСomponent value) {
        componentMap.put(value.getId(), value);
    }

    @Override
    public ExtСomponent get(Integer key) {
        return componentMap.get(key);
    }

    @Override
    public void remove(Integer key) {
        componentMap.remove(key);
    }

    @Override
    public Collection<ExtСomponent> values() {
        return componentMap.values();
    }

    public void init() {
        List<ExtСomponent> components = new ArrayList<>(List.of(
                new ExtСomponent("ham", 25),
                new ExtСomponent("mozzarella", 25),
                new ExtСomponent("champignons", 10),
                new ExtСomponent("pepper salami", 25),
                new ExtСomponent("rukola", 10)
        ));
        //componentMap = components.stream().collect(Collectors.toMap(ExtСomponent::getId, с -> с));
        components.forEach(component -> put(component));
    }
}
