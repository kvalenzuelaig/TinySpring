package cat.tecnocampus.tinySpring.webProject;

import cat.tecnocampus.application.MyController;
import cat.tecnocampus.tinySpring.core.ComponentFactory;

import java.lang.reflect.Method;

// Simple web framework
public class LiteSpringBoot {
    private final ComponentFactory componentFactory;

    public LiteSpringBoot(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }

    public HttpResponse handleRequest(HttpRequest request) {
        // Find the appropriate request handler and invoke it
        Object handler = componentFactory.getContainer().getComponentOfType(requestHandlerFor(request));
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
