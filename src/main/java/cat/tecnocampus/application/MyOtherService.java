package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Service;
import cat.tecnocampus.tinySpring.validationAOP.Length;
import cat.tecnocampus.tinySpring.validationAOP.Validated;

@Service
@Validated
public class MyOtherService implements cat.tecnocampus.application.Service {
    @Override
    public void doSomething(@Length(min = 5, max = 10) String param) {
        System.out.println("Parameter length is between 5 and 10. My other service doing something else with: " + param);
    }
}
