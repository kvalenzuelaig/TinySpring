package cat.tecnocampus.tinySpring.core;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Simple DI container
class ApplicationContainer {
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
        Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .forEach(f -> autowireField(instance, f));
    }

    private void autowireField(Object instance, Field field) {
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
