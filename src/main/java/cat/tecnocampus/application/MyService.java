package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;
import cat.tecnocampus.tinySpring.core.annotation.Service;

@Service
public class MyService {
    @Autowired
    MyOtherServiceInterface myOtherService;

    public void doSomething(String param) {
        System.out.println("MyService doing something with: " + param);
        myOtherService.doSomething(param);
    }
}
