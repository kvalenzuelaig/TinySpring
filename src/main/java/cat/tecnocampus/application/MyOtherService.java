package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Service;
import cat.tecnocampus.tinySpring.validationAOP.Validated;

@Service
@Validated
public class MyOtherService implements cat.tecnocampus.application.Service {
    @Override
    public void doSomething(String param) {
        System.out.println("Parameter is validated. My other service doing something else with: " + param);
    }
}
