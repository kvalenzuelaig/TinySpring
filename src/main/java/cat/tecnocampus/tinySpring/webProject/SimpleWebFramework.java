package cat.tecnocampus.tinySpring.webProject;

import cat.tecnocampus.application.MyController;
import cat.tecnocampus.tinySpring.core.ApplicationContextContainer;
import cat.tecnocampus.tinySpring.core.ComponentFactory;
import cat.tecnocampus.tinySpring.core.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SimpleWebFramework {
    private final ApplicationContextContainer contextContainer;

    public SimpleWebFramework(ApplicationContextContainer contextContainer) {
        this.contextContainer = contextContainer;
    }

    public HttpResponse handleRequest(HttpRequest request) {
        Object handler = requestHandlerFor(request);
        if (handler != null) {
            try {
                Method[] methods = handler.getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                        if (request.path.startsWith(mapping.value())) {
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

    private Object requestHandlerFor(HttpRequest request) {
        //we could have more than one RestController and should return the one that matches the request
        return contextContainer.getComponents().stream()
                .filter(c -> c.getClass().isAnnotationPresent(RestController.class))
                .findFirst().orElseThrow(() -> new RuntimeException("No RestController found"));
    }
}
