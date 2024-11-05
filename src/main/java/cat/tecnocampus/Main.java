
package cat.tecnocampus;

import cat.tecnocampus.tinySpring.*;
import cat.tecnocampus.tinySpring.core.LiteSpringBoot;

public class Main {
    public static void main(String[] args) {
        LiteSpringBoot app = new LiteSpringBoot("cat.tecnocampus");
        HttpRequest request = new HttpRequest("GET", "/hello");
        HttpResponse response = app.handleRequest(request);
        System.out.println("Response: " + response.getBody());
    }
}
