/**
 * Copyright © 2015-2017 Masamitsu Namioka (masamitsunamioka@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelReader;
import org.apache.maven.model.io.ModelReader;

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

    public static String resolveVersion(String s, String version) {
        if (P.matcher(s).find()) {
            return s.replaceAll(R, String.format("-%s.jar", version));
        } else {
            return s;
        }
    }
}
