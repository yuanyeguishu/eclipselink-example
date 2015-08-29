package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@lombok.extern.slf4j.Slf4j
@Dependent
@Interceptor @Trace
public class TraceInterceptor extends AbstractInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Throwable {
        long s = System.currentTimeMillis();
        if (log.isTraceEnabled()) {
            log.trace("[ENTERING] {}#{}",
                    invocationContext.getTarget().getClass().getName(),
                    invocationContext.getMethod().getName());
        }
        try {
            return invocationContext.proceed();
        } finally {
            long e = System.currentTimeMillis();
            if (log.isTraceEnabled()) {
                log.trace("[EXITING] {}#{} in {}(ms)",
                        invocationContext.getTarget().getClass().getName(),
                        invocationContext.getMethod().getName(),
                        (e - s));
            }
        }
    }
}
