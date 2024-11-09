package cat.tecnocampus.tinySpring.validationAOP;

import cat.tecnocampus.tinySpring.core.ComponentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ValidationHandler implements InvocationHandler {
    private final Object target;
    private final Logger logger = LoggerFactory.getLogger(ValidationHandler.class);

    public ValidationHandler(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int index = 0;
        for (Parameter parameter : method.getParameters()) {
            if (parameter.isAnnotationPresent(Length.class)) {
                Length length = parameter.getAnnotation(Length.class);
                int min = length.min();
                int max = length.max();
                if (args[index] == null || args[index].toString().length() < min || args[index].toString().length() > max) {
                    logger.info("Parameter NOT validated: {}", args[index].toString());
                    throw new IllegalArgumentException("Parameter length is not between " + min + " and " + max);
                }
                logger.info("Parameter validated: {}", args[index].toString());
            }
            index ++;
        }
        return method.invoke(target, args);
    }
}
