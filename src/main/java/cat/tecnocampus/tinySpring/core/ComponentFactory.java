package cat.tecnocampus.tinySpring.core;

import java.io.IOException;
import java.util.Set;

public class ComponentFactory {
    private final ApplicationContainer applicationContainer;
    private final ComponentScan componentScan;

    public ComponentFactory(String packageName) {
        this.applicationContainer = new ApplicationContainer();
        this.componentScan = new ComponentScan(packageName);

        Set<Class<?>> componentClasses = getComponentClasses();
        componentClasses.stream()
                .map(this::newComponentObject)
                .forEach(o -> applicationContainer.register(o.getClass(), o));

        applicationContainer.getInstances().values().forEach(applicationContainer::autowire);
    }

    private Object newComponentObject(Class<?> clazz) {
        Object componentObject = null;
        try {
            componentObject = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return componentObject;
    }

    private Set<Class<?>> getComponentClasses() {
        Set<Class<?>> componentClasses;
        try {
            componentClasses = componentScan.componentScan();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return componentClasses;
    }

    public ApplicationContainer getContainer() {
        return applicationContainer;
    }
}
