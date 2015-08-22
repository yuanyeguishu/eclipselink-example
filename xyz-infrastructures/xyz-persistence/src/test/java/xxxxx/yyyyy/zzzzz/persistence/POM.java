package xxxxx.yyyyy.zzzzz.persistence;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelReader;
import org.apache.maven.model.io.ModelReader;

@lombok.extern.slf4j.Slf4j
public final class POM {

    private static final String R = "-[0-9]+\\.jar";
    private static final Pattern P = Pattern.compile(R);

    private POM() {
    }

    private static Model getModel() {
        ModelReader modelReader = new DefaultModelReader();
        try {
            return modelReader.read(new File("pom.xml"), null);
        } catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }

    public static String getArtifactId() {
        Model model = getModel();
        return model.getArtifactId();
    }

    public static String getVersion() {
        Model model = getModel();
        return (model.getVersion() != null) ? model.getVersion() : model.getParent().getVersion();
    }

    public static String resolveVersion(final String s, final String version) {
        if (P.matcher(s).find()) {
            return s.replaceAll(R, String.format("-%s.jar", version));
        } else {
            return s;
        }
    }
}
