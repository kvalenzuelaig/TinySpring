package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Component;
import cat.tecnocampus.tinySpring.validationAOP.Validated;

@Component
@Validated
public class MyOtherService implements MyOtherServiceInterface {
    @Override
    public void doSomething(String param) {
        System.out.println("Parameter is validated. My other service doing something else with: " + param);
    }
}
