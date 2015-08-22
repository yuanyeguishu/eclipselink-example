package xxxxx.yyyyy.zzzzz.apt;

import static javax.lang.model.SourceVersion.*;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedSourceVersion(RELEASE_8)
@SupportedAnnotationTypes("*")
public class SampleProcessor extends AbstractProcessor {

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        annotations.stream().forEach(x -> {
            messager.printMessage(Diagnostic.Kind.WARNING, String.format("############ %s", x.getQualifiedName().toString()));
        });
        roundEnv.getRootElements().stream().forEach(x -> {
            messager.printMessage(Diagnostic.Kind.WARNING, String.format("############ %s", x.asType().toString()));
        });
        return true;
    }
}
