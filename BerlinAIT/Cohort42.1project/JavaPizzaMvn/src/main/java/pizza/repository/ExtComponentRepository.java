package pizza.repository;

import pizza.domain.ExtComponent;

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
public class ExtComponentRepository implements CrudRepository<Integer, ExtComponent> {
    private Map<Integer, ExtComponent> componentMap;

    public ExtComponentRepository() {
        this.componentMap = new HashMap<>();
        init();
    }

    @Override
    public void save(ExtComponent component) {
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
    }

    @Override
    public ExtComponent findById(Integer key) {
        return componentMap.get(key);
    }

    @Override
    public void deleteById(Integer key) {
        componentMap.remove(key);
    }

    @Override
    public Collection<ExtComponent> findAll() {
        return componentMap.values();
    }

    @Override
    public void deleteAll() {
        throw new NullPointerException("Method not implemented");
    }

    private void init() {
        List<ExtComponent> components = new ArrayList<>(List.of(
                new ExtComponent("ham", 25),
                new ExtComponent("mozzarella", 25),
                new ExtComponent("champignons", 10),
                new ExtComponent("pepper salami", 25),
                new ExtComponent("rukola", 10)
        ));
        components.forEach(component -> save(component));
    }
}
