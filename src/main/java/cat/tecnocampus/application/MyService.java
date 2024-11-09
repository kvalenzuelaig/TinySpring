package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.core.annotation.Autowired;
import cat.tecnocampus.tinySpring.core.annotation.Service;

@Service
public class MyService {
    @Autowired
    MyOtherServiceInterface myOtherService;

    public String doSomething(String param) {
        return myOtherService.doSomething(param + " MS");
    }
}
