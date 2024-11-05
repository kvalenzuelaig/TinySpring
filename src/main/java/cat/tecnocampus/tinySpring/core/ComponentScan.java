package cat.tecnocampus.tinySpring.core;

import cat.tecnocampus.tinySpring.core.annotation.Component;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComponentScan {
    private String basePackage;


    public ComponentScan(String basePackage) {
        this.basePackage = basePackage;
    }

    public Set<Class<?>> componentScan() throws ClassNotFoundException, IOException {
        Set<Class<?>> components = new HashSet<>();

        List<File> classes = getFilesOfClassesInBasePackage();
        classes.stream().map(this::getClassFromFile).filter(this::isComponent).forEach(c -> components.add(c));
        return components;
    }

    private boolean isComponent(Class<?> clazz) {

        if (clazz.isAnnotationPresent(Component.class)) {
            return true;
        }
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            return isComponent(annotation.annotationType());
        }
        return false;
    }

    private List<File> getFilesOfClassesInBasePackage() {
        String path = basePackage.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("No resource for " + path);
        }
        File directory = new File(resource.getFile());
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + directory);
        }

        return Arrays.stream(directory.listFiles()).filter(f -> f.getName().endsWith(".class")).toList();
    }

    private Class<?> getClassFromFile(File file) {
        String className = basePackage + '.' + file.getName().substring(0, file.getName().length() - 6);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}