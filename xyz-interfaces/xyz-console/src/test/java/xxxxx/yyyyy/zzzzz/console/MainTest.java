package xxxxx.yyyyy.zzzzz.console;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import org.junit.Test;

@lombok.extern.slf4j.Slf4j
public class MainTest {

    @Test
    public void test() {
        log.info(new File(".").getAbsolutePath());
        x("xxxxx.yyyyy.zzzzz").stream()
                .forEach(x -> {
                    Stream.of(x.getInterfaces()).filter(y -> y != null && y != Object.class).forEach(y -> log.info("{} implements {}", x.getName(), y.getName()));
                    Stream.of(x.getSuperclass()).filter(y -> y != null && y != Object.class).forEach(y -> log.info("{} extends    {}", x.getName(), y.getName()));
                });
    }

    private List<Class<?>> x(final String s) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            Enumeration<URL> e = Thread.currentThread().getContextClassLoader().getResources(s);
            while (e.hasMoreElements()) {
                URL url = e.nextElement();
                //log.info("{}", url);
                if ("jar".equals(url.getProtocol())) {
                    try (JarFile jarFile = new JarFile(url.toString().replaceAll("(^jar:file:|!.*$)", ""))) {
                        // TODO nested jar
                        jarFile.stream()
                                .filter(x -> x.toString().endsWith(".class"))
                                .map(x -> x.toString().replaceAll(url.toString(), ""))
                                //-----
                                .map(x -> x.replaceAll("/", ".").replaceAll("\\.class$", ""))
                                .filter(x -> !x.matches("(.*Test$|.*IT$)"))
                                .filter(x -> x.matches(".*\\.Sample.*"))
                                //.peek(x -> log.info("{}", x))
                                .forEach(x -> classes.add(forName(x)));
                    }
                } else {
                    Files.walk(new File(url.toURI()).toPath())
                            .filter(x -> x.toString().endsWith(".class"))
                            .map(x -> x.toString().replaceAll(url.toString().replaceAll("file:", ""), s))
                            //-----
                            .map(x -> x.replaceAll("/", ".").replaceAll("\\.class$", ""))
                            .filter(x -> !x.matches("(.*Test$|.*IT$)"))
                            .filter(x -> x.matches(".*\\.Sample.*"))
                            //.peek(x -> log.info("{}", x))
                            .forEach(x -> classes.add(forName(x)));
                }
            }
            return classes;
        } catch (IOException | URISyntaxException cause) {
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
}
