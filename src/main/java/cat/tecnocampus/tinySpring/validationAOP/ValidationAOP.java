package cat.tecnocampus.tinySpring.validationAOP;

import cat.tecnocampus.tinySpring.core.ApplicationContextContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationAOP {
    private static final Logger logger = LoggerFactory.getLogger(ValidationAOP.class);

    public static void createAndRegisterValidationProxies(ApplicationContextContainer applicationContext) {
        List<Object> componentsToProcess = applicationContext.getComponents().stream()
                .filter(c -> c.getClass().isAnnotationPresent(Validated.class))
                .collect(Collectors.toList());
        componentsToProcess.forEach(c -> createAndRegisterProxy(c, applicationContext));
    }

    private static void createAndRegisterProxy(Object target, ApplicationContextContainer applicationContext) {
        Object proxy = ValidationProxyFactory.createProxy(target);
        logger.info("Created proxy for class: {}", target.getClass().getName());
        Class<?> clazz = proxy.getClass().getInterfaces()[0];
        applicationContext.register(clazz, proxy);
    }
}
