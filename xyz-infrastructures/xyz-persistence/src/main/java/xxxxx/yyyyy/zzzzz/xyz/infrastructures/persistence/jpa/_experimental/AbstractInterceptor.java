package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.interceptor.InvocationContext;

@lombok.extern.slf4j.Slf4j
public abstract class AbstractInterceptor {

    protected <T extends Annotation> T invokeAnnotation(InvocationContext ic, Class<T> annotationClass) {
        Method m = ic.getMethod();
        if (m != null && m.isAnnotationPresent(annotationClass)) {
            return m.getAnnotation(annotationClass);
        }
        Class<?> c = ic.getTarget().getClass();
        if (c.isAnnotationPresent(annotationClass)) {
            return c.getAnnotation(annotationClass);
        }
        throw new IllegalStateException();
    }
}
