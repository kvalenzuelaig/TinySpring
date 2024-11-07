package cat.tecnocampus.tinySpring.webProject;

import cat.tecnocampus.application.MyController;
import cat.tecnocampus.tinySpring.core.ApplicationContextContainer;
import cat.tecnocampus.tinySpring.core.ComponentFactory;

import java.lang.reflect.Method;

public class SimpleWebFramework {
    private final ApplicationContextContainer contextContainer;

    public SimpleWebFramework(ApplicationContextContainer contextContainer) {
        this.contextContainer = contextContainer;
    }

    public HttpResponse handleRequest(HttpRequest request) {
        // Find the appropriate request handler and invoke it
        Object handler = contextContainer.getComponentOfType(requestHandlerFor(request));
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

    private Class<?> requestHandlerFor(HttpRequest request) {
        // Simple logic to find the request handler based on the request path
        return MyController.class; // TODO: get the controller from the application context
    }
}
