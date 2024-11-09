package cat.tecnocampus.tinySpring.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContextContainer {
    private Map<Class<?>, Object> instances = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(ApplicationContextContainer.class);

    public void register(Class<?> clazz, Object instance) {
        logger.info("Registered component: {}", clazz.getName());
        instances.put(clazz, instance);
    }

    public void unregister(Class<?> clazz) {
        logger.info("Unregistered component: {}", clazz.getName());
        instances.remove(clazz);
    }

    public Object getComponentOfType(Class<?> clazz) {
        return instances.get(clazz);
    }

    public Collection<Object> getComponents() {
        return instances.values();
    }

}
