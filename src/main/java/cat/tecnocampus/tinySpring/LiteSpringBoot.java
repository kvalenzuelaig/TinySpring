package cat.tecnocampus.tinySpring;

import cat.tecnocampus.application.MyController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

// Simple web framework
public class LiteSpringBoot {
    private Container container = new Container();
    //private ComponentScanReflexionLibrary componentScan = new ComponentScanReflexionLibrary("cat.tecnocampus.application");
    private ComponentScan componentScan = new ComponentScan("cat.tecnocampus.application");

    public LiteSpringBoot(String basePackage) {

        Set<Class<?>> componentClasses = null;
        try {
            componentClasses = componentScan.componentScan();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Register all components
        for (Class<?> clazz : componentClasses) {
            if (clazz.isAnnotationPresent(Component.class) || clazz.isAnnotationPresent(Service.class)) {
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    container.register(clazz, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Autowire dependencies after registering components
        for (Object instance : container.getInstances().values()) {
            container.autowire(instance);
        }
    }

    public HttpResponse handleRequest(HttpRequest request) {
        // Find the appropriate request handler and invoke it
        Object handler = container.getBean(requestHandlerFor(request));
        if (handler != null) {
            try {
                Method[] methods = handler.getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                        if (mapping.value().equals(request.path)) {
                            return (HttpResponse) method.invoke(handler, request);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Class<?> requestHandlerFor(HttpRequest request) {
        // Simple logic to find the request handler based on the request path
        return MyController.class; // Replace with your own logic
    }
}
