package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Component;
import cat.tecnocampus.tinySpring.validationAOP.Validated;

@Component
@Validated
public class MyOtherService implements MyOtherServiceInterface {
    @Override
    public String doSomething(String param) {
        return param + " MOS";
    }
}
