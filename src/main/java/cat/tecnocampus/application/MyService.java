package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;
import cat.tecnocampus.tinySpring.core.annotation.Component;

@Component
public class MyService {
    @Autowired
    Service myOtherService;

    public void doSomething(String param) {
        System.out.println("MyService doing something with: " + param);
        myOtherService.doSomething(param);
    }
}
