package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@lombok.extern.slf4j.Slf4j
@Dependent
@Interceptor @Valid
public class ValidationInterceptor extends AbstractInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Throwable {
        Valid valid = invokeAnnotation(invocationContext, Valid.class);
        if (!valid.ignore()) {
            Class<?>[] parameterTypes = invocationContext.getMethod().getParameterTypes();
            if (parameterTypes == null) {
                throw new IllegalStateException();
            }
            Object[] parameters = invocationContext.getParameters();
            if (parameters == null) {
                throw new IllegalStateException();
            }
            if (parameterTypes.length != parameters.length) {
                if (log.isTraceEnabled()) {
                    log.trace("parameterTypes.length -> {}", parameterTypes.length);
                    log.trace("parameters.length -> {}", parameters.length);
                }
                throw new IllegalStateException();
            }
            List<ParameterContext> parameterContexts = new ArrayList<>();
            for (int i = 0; i < parameterTypes.length; i++) {
                parameterContexts.add(new ParameterContext(parameterTypes[i], parameters[i]));
            }
            if (/*parameters != null &&*/parameters.length > 0) {
                validate(valid, parameterContexts);
            }
        }
        return invocationContext.proceed();
    }

    private void validate(Valid valid, List<ParameterContext> parameterContexts) {
        List<String> messages = new ArrayList<>();
        Stream.iterate(0, i -> i + 1).limit(parameterContexts.size()).forEach(i -> {
            ParameterContext parameterContext = parameterContexts.get(i);
            if (parameterContext.getParameter() == null) {
                // TODO An index of parameter
                String message = String.format("A null parameter of %s detected", parameterContext.getParameterType());
                if (log.isTraceEnabled()) {
                    log.trace("message -> {}", message);
                }
                messages.add(message);
            } else {
                // TODO
            }
        });
        if (!messages.isEmpty()) {
            throw new IllegalArgumentException(messages.stream().collect(joining("\n", "\n", "\n")));
        }
    }

    @lombok.Value
    //    @lombok.EqualsAndHashCode
    //    @lombok.ToString
    //    @lombok.Getter
    private static class ParameterContext {

        private final Class<?> parameterType;
        private final Object parameter;
//
//        public ParameterContext(Class<?> parameterType, Object parameter) {
//            this.parameterType = parameterType;
//            this.parameter = parameter;
//        }
    }
}
