package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.*;

import java.util.Map;

// Example controller
@Component
public class MyController {
    @Autowired
    private MyService myService;

    @RequestMapping("/hello")
    public HttpResponse handleRequest(HttpRequest request) {
        System.out.println("Handling request for path: " + request.getPath());
        myService.doSomething();

        Map<String, Object> responseBody = Map.of("message", "Hello, World!");
        return new HttpResponse(200, responseBody);
    }
}
