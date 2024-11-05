package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.Component;
import cat.tecnocampus.tinySpring.Service;

@Service
public class MyService {
    public void doSomething() {
        System.out.println("MyService doing something...");
    }
}
