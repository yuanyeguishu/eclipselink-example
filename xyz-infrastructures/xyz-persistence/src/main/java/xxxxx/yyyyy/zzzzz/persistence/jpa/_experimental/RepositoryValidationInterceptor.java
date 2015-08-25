package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@lombok.extern.slf4j.Slf4j
@Dependent
@Interceptor @RepositoryValidation
public class RepositoryValidationInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        RepositoryValidation repositoryValidation = declaredAnnotation(ic, RepositoryValidation.class);
        if (repositoryValidation != null) {
            if (log.isDebugEnabled()) {
                log.debug("repositoryValidation -> flagA:{}, flagB:{}", repositoryValidation.flagA(), repositoryValidation.flagB());
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("repositoryValidation is null");
            }
        }
//        long s = System.currentTimeMillis();
        try {
//            Stream.of(ic.getParameters()).forEach(x -> {
//                if (log.isDebugEnabled()) {
//                    log.debug("arguments -> {}", x.getClass().getName());
//                }
//            });
            return ic.proceed();
        } finally {
//            long e = System.currentTimeMillis();
//            log.info("{}#{} elapsed time in {} ms",
//                    ic.getTarget().getClass().getName(), ic.getMethod().getName(), String.valueOf(e - s));
        }
    }

    private <T extends Annotation> T declaredAnnotation(InvocationContext ic, Class<T> interceptorBinding) {
        Method m = ic.getMethod();
        if (m != null && m.isAnnotationPresent(interceptorBinding)) {
            return m.getAnnotation(interceptorBinding);
        }
        Class<?> c = ic.getTarget().getClass();
        if (c.isAnnotationPresent(interceptorBinding)) {
            return c.getAnnotation(interceptorBinding);
        }
        return null;
    }
}
