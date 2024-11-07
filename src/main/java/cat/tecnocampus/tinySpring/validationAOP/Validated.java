package cat.tecnocampus.tinySpring.validationAOP;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Validated {
}
