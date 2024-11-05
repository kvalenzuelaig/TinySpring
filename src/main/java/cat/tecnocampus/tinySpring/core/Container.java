package cat.tecnocampus.tinySpring.core;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Simple DI container
class Container {
    private Map<Class<?>, Object> instances = new HashMap<>();

    public void register(Class<?> clazz, Object instance) {
        instances.put(clazz, instance);
    }

    public Object getBean(Class<?> clazz) {
        return instances.get(clazz);
    }

    public Map<Class<?>, Object> getInstances() {
        return instances;
    }

    public void autowire(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = instances.get(field.getType());
                if (dependency != null) {
                    field.setAccessible(true);
                    try {
                        field.set(instance, dependency);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
