package xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@lombok.extern.slf4j.Slf4j
@Dependent
@Interceptor @Lifecycle
public class LifecycleInterceptor {

    @AroundConstruct
    public Object aroundConstruct(InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } finally {
            if (log.isTraceEnabled()) {
                log.trace("[Lifecycle][@AroundConstruct] {}", ctx.getTarget().toString());
            }
        }
    }

    @PostConstruct
    public Object postConstruct(InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } finally {
            if (log.isTraceEnabled()) {
                log.trace("[Lifecycle][@PostConstruct]   {}", ctx.getTarget().toString());
            }
        }
    }

    @PreDestroy
    public Object preDestroy(InvocationContext ctx) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("[Lifecycle][@PreDestroy]      {}", ctx.getTarget().toString());
        }
        return ctx.proceed();
    }
}
