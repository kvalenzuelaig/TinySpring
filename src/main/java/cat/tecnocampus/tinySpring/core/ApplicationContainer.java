package cat.tecnocampus.tinySpring.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContainer {
    private Map<Class<?>, Object> instances = new HashMap<>();

    public void register(Class<?> clazz, Object instance) {
        instances.put(clazz, instance);
    }

    public Object getComponentOfType(Class<?> clazz) {
        return instances.get(clazz);
    }

    public Collection<Object> getComponents() {
        return instances.values();
    }

}
