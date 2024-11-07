package cat.tecnocampus.tinySpring.validationAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ValidationHandler implements InvocationHandler {
    private final Object target;

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
                    throw new IllegalArgumentException("Parameter length is not between " + min + " and " + max);
                }
            }
            index ++;
        }
        return method.invoke(target, args);
    }
}
