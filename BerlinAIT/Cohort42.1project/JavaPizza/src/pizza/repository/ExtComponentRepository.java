package pizza.repository;

import pizza.domain.ExtСomponent;

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
    public ExtСomponent save(ExtСomponent component) {
        if (component.getId() == null) {
            int componentId = 0;
            for (Integer id : componentMap.keySet()) {
                if (componentId < id) {
                    componentId = id;
                }
            }
            component.setId(componentId + 1);
        }
        componentMap.put(component.getId(), component);
        return component;
    }

    @Override
    public ExtСomponent findById(Integer key) {
        return componentMap.get(key);
    }

    @Override
    public void remove(Integer key) {
        componentMap.remove(key);
    }

    @Override
    public Collection<ExtСomponent> findAll() {
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
        components.forEach(component -> save(component));
    }
}
