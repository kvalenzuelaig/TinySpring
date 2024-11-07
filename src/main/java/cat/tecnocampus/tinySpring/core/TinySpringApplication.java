package cat.tecnocampus.tinySpring.core;

import cat.tecnocampus.tinySpring.webModule.HttpRequest;
import cat.tecnocampus.tinySpring.webModule.HttpResponse;
import cat.tecnocampus.tinySpring.webModule.SimpleWebFramework;

public class TinySpringApplication {
    private static ComponentFactory componentFactory;

    public static void run(Class<?> clazz, String[] args) {
        componentFactory = new ComponentFactory(clazz.getPackageName() + ".application");
        componentFactory.buildContext();

        SimpleWebFramework app = new SimpleWebFramework(componentFactory.getContextContainer());
        HttpRequest request = new HttpRequest("GET", "/hello/how are you");
        HttpResponse response = app.handleRequest(request);
        System.out.println(String.format("Response: status-> %s, body-> %s", response.getStatusCode(), response.getBody()));
    }
}
