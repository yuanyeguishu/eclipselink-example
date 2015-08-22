package xxxxx.yyyyy.zzzzz.console;

import static java.util.stream.Collectors.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

@lombok.extern.slf4j.Slf4j
public final class ServiceLocator {

    private static final Map<Class<?>, ServiceLoader<? extends Object>> CACHE = new HashMap<>();
    private static final String PREFIX = "META-INF/services/";

    static {
        reload();
    }

    private static synchronized void reload() {
        CACHE.clear();
        try {
            Enumeration<URL> e = Thread.currentThread().getContextClassLoader().getResources(PREFIX);
            while (e.hasMoreElements()) {
                URL url = e.nextElement();
                if ("jar".equals(url.getProtocol())) {
                    try (JarFile jarFile = new JarFile(url.toString().replaceAll("(^jar:file:|!.*$)", ""))) {
                        jarFile.stream()
                                .filter(x -> Pattern.compile(PREFIX + ".+").matcher(x.getName()).find())
                                .map(x -> x.getName().replaceAll(PREFIX, ""))
                                .map(x -> forName(x))
                                .forEach(x -> CACHE.put(x, ServiceLoader.load(x)));
                    }
                } else {
                    Files.list(new File(url.toURI()).toPath())
                            .map(x -> x.toString().replaceAll("\\\\", "/").replaceAll("^.*" + PREFIX, ""))
                            .map(x -> forName(x))
                            .forEach(x -> CACHE.put(x, ServiceLoader.load(x)));
                }
            }
        } catch (URISyntaxException | IOException cause) {
            throw new RuntimeException(cause);
        }
    }

    private static Class<?> forName(final String s) {
        try {
            return Class.forName(s);
        } catch (ClassNotFoundException cause) {
            throw new RuntimeException(cause);
        }
    }

    public static <S> S get(final Class<S> c) {
        ServiceLoader<S> sl = (ServiceLoader<S>) CACHE.get(c);
        if (sl == null) {
            // TODO return null?
            throw new IllegalStateException();
        }
        List<S> l = StreamSupport.stream(sl.spliterator(), false).collect(toList());
        if (l.size() > 1) {
            throw new IllegalStateException();
        }
        return l.get(0);
    }
}
