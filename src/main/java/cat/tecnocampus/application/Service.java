package cat.tecnocampus.application;

import cat.tecnocampus.tinySpring.validationAOP.Length;

public interface Service {
    void doSomething(@Length(min = 5, max = 20) String param);
}