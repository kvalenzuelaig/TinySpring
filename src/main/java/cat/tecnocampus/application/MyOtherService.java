package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Service;

@Service
public class MyOtherService {
    public void doSomethingElse() {
        System.out.println("My other service doing something else...");
    }
}
