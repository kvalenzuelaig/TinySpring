package cat.tecnocampus.tinySpring;

import cat.tecnocampus.tinySpring.core.ComponentFactory;
import cat.tecnocampus.tinySpring.validationAOP.ValidationAOP;
import cat.tecnocampus.tinySpring.webModule.HttpRequest;
import cat.tecnocampus.tinySpring.webModule.HttpResponse;
import cat.tecnocampus.tinySpring.webModule.SimpleWebFramework;

public class TinySpringFramework {
    private static ComponentFactory componentFactory;

    public static void run(Class<?> clazz, String[] args) {
        //Creates the application context
        componentFactory = new ComponentFactory(clazz.getPackageName() + ".application");
        componentFactory.buildContext();

        // Creates proxies for the components that need validation
        ValidationAOP.createAndRegisterValidationProxies(componentFactory.getContextContainer());

        // Injects the dependencies
        componentFactory.injectDependencies();

        // Creates the web server or framework
        SimpleWebFramework app = new SimpleWebFramework(componentFactory.getContextContainer());

        // With a real web server, these calls would be performed from a REST client
        System.out.println();
        HttpRequest request = new HttpRequest("GET", "/hello/how are you");
        HttpResponse response = app.handleRequest(request);
        System.out.println(String.format("Response: status-> %s, body-> %s", response.getStatusCode(), response.getBody()));

        System.out.println();
        request = new HttpRequest("GET", "/hello/TinySpring");
        response = app.handleRequest(request);
        System.out.println(String.format("Response: status-> %s, body-> %s", response.getStatusCode(), response.getBody()));
    }
}
