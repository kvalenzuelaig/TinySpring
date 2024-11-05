package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;
import cat.tecnocampus.tinySpring.core.annotation.Service;

@Service
public class MyService {
    @Autowired
    MyOtherService myOtherService;

    public void doSomething() {
        System.out.println("MyService doing something...");
        myOtherService.doSomethingElse();
    }
}
