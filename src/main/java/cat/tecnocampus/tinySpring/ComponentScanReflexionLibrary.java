package cat.tecnocampus.tinySpring;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Set;

public class ComponentScanReflexionLibrary {
    private Reflections reflections;
    private String basePackage;

    public ComponentScanReflexionLibrary(String basePackage) {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(basePackage))
                .filterInputsBy(new FilterBuilder().includePackage(basePackage))
                .setScanners(Scanners.TypesAnnotated));
    }

    public Set<Class<?>> componentScan() {
        return reflections.getTypesAnnotatedWith(Component.class);
    }
}
