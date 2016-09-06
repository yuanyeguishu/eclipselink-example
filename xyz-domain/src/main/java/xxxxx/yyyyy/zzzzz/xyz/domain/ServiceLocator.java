package xxxxx.yyyyy.zzzzz.xyz.domain;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@lombok.extern.slf4j.Slf4j
public final class ServiceLocator {

    private static final Map<Class<?>, List<?>> CACHE = new ConcurrentHashMap<>();

    public static <S> List<S> get(Class<S> clazz) {
        return (List<S>) CACHE.computeIfAbsent(clazz, x -> {
            ServiceLoader<S> serviceLoader = ServiceLoader.load(clazz);
            List<S> services = StreamSupport.stream(serviceLoader.spliterator(), false).collect(Collectors.toList());
            if (services == null || services.isEmpty()) {
                throw new IllegalArgumentException(clazz.getName());
            }
            return services;
        });
    }
//
//    private static final Map<Class<?>, ServiceLoader<? extends Object>> CACHE = new ConcurrentHashMap<>();
//    private static final String PREFIX = "META-INF/services/";
//
//    static {
//        try {
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            Enumeration<URL> e = classLoader.getResources(PREFIX);
//            while (e.hasMoreElements()) {
//                URL url = e.nextElement();
//                if ("jar".equals(url.getProtocol())) {
//                    if (url.toString().matches(".*lombok-[0-9.]+\\.jar.*")) {
//                        if (log.isTraceEnabled()) {
//                            log.trace(String.format("ignore -> %s", url.toString()));
//                        }
//                        continue;
//                    }
//                    try (JarFile jarFile = new JarFile(url.toString().replaceAll("(^jar:file:|!.*$)", ""))) {
//                        jarFile.stream()
//                                .filter(x -> Pattern.compile(PREFIX + ".+").matcher(x.getName()).find())
//                                .map(x -> x.getName().replaceAll(PREFIX, ""))
//                                .map(x -> forName(x))
//                                .forEach(x -> CACHE.put(x, ServiceLoader.load(x)));
//                    }
//                } else {
//                    Files.list(Paths.get(url.toURI()))
//                            .map(x -> x.toString().replaceAll("\\\\", "/").replaceAll("^.*" + PREFIX, ""))
//                            .map(x -> forName(x))
//                            .forEach(x -> CACHE.put(x, ServiceLoader.load(x)));
//                }
//            }
//        } catch (URISyntaxException | IOException cause) {
//            throw new RuntimeException(cause);
//        }
//    }
//
//    private static Class<?> forName(String s) {
//        try {
//            return Class.forName(s);
//        } catch (ClassNotFoundException cause) {
//            throw new RuntimeException(cause);
//        }
//    }
//
//    public static <S> List<S> get(Class<S> c) {
//        ServiceLoader<S> serviceLoader = (ServiceLoader<S>) CACHE.get(c);
//        if (serviceLoader == null) {
//            throw new IllegalArgumentException();
//        }
//        return StreamSupport.stream(serviceLoader.spliterator(), false).collect(toList());
//    }
}
