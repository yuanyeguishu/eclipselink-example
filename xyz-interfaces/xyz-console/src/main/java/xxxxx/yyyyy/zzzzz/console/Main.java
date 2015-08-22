package xxxxx.yyyyy.zzzzz.console;

import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.stream.Stream;
import xxxxx.yyyyy.zzzzz.application.service.SampleService;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;

public class Main {

    private final SampleService sampleService;

    public Main() {
        //ServiceLocator.get(String.class);
        sampleService = ServiceLocator.get(SampleService.class);
        inject(sampleService, SampleRepository.class, ServiceLocator.get(SampleRepository.class));
    }

    public void main0(String[] args) {
        sampleService.service().stream().forEach(System.out::println);
    }

    private void inject(Object target, Class<?> c, Object o) {
        Stream.of(target.getClass().getDeclaredFields())
                .filter(f -> f.getType() == c)
                .peek(f -> {
                    if (Modifier.isPrivate(f.getModifiers())) {
                        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                            f.setAccessible(true);
                            return null;
                        });
                    }
                })
                .forEach(f -> {
                    try {
                        f.set(target, o);
                    } catch (IllegalArgumentException | IllegalAccessException cause) {
                        throw new RuntimeException(cause);
                    }
                });
    }

    public static void main(String[] args) {
        new Main().main0(args);
    }
}
