package xxxxx.yyyyy.zzzzz.persistence.jpa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@lombok.extern.slf4j.Slf4j
@Dependent
@Interceptor @ArgumentsValidation
public class ArgumentsValidationInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        ArgumentsValidation argumentsValidation = declaredAnnotation(ic, ArgumentsValidation.class);
        if (argumentsValidation != null) {
            if (log.isDebugEnabled()) {
                log.debug("argumentsValidation -> flagA:{}, flagB:{}", argumentsValidation.flagA(), argumentsValidation.flagB());
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("argumentsValidation is null");
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
