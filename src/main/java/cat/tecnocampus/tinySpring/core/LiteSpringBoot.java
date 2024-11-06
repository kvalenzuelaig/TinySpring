package cat.tecnocampus.tinySpring.core;

import cat.tecnocampus.application.MyController;
import cat.tecnocampus.tinySpring.HttpRequest;
import cat.tecnocampus.tinySpring.HttpResponse;
import cat.tecnocampus.tinySpring.RequestMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

// Simple web framework
public class LiteSpringBoot {
    private final ComponentFactory componentFactory;

    public LiteSpringBoot(String basePackage) {
        componentFactory = new ComponentFactory(basePackage);

    }

    public HttpResponse handleRequest(HttpRequest request) {
        // Find the appropriate request handler and invoke it
        Object handler = componentFactory.getContainer().getBean(requestHandlerFor(request));
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
