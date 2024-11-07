package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;
import cat.tecnocampus.tinySpring.core.annotation.RestController;
import cat.tecnocampus.tinySpring.webModule.HttpRequest;
import cat.tecnocampus.tinySpring.webModule.HttpResponse;
import cat.tecnocampus.tinySpring.webModule.RequestMapping;

import java.util.Map;

@RestController
public class MyController {
    @Autowired
    private MyService myService;

    @RequestMapping("/hello")
    public HttpResponse handleRequest(HttpRequest request) {
        System.out.println("Handling request for path: " + request.getPath());
        String param = getStringAfterLastSlash(request.getPath());
        myService.doSomething(param);

        Map<String, Object> responseBody = Map.of("message", "Hello, World!");
        return new HttpResponse(200, responseBody);
    }

    public static String getStringAfterLastSlash(String input) {
        int lastIndex = input.lastIndexOf('/');
        if (lastIndex != -1 && lastIndex < input.length() - 1) {
            return input.substring(lastIndex + 1);
        }
        return "";
    }
}
