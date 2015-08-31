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
            Object[] parameters = invocationContext.getParameters();
            // TODO This is necessary?
            //if (parameterTypes.length != parameters.length) {
            //    throw new IllegalStateException();
            //}
            if (parameterTypes.length > 0) {
                List<String> messages = validate(valid, parameterTypes, parameters);
                if (!messages.isEmpty()) {
                    throw new IllegalArgumentException(messages.stream().collect(joining("\n", "\n", "\n")));
                }
            }
        }
        return invocationContext.proceed();
    }

    private List<String> validate(Valid valid, Class<?>[] parameterTypes, Object[] parameters) {
        List<String> messages = new ArrayList<>();
        Stream.iterate(0, i -> i + 1).limit(parameterTypes.length).forEach(i -> {
            if (parameters[i] == null) {
                // TODO An index of parameter
                String message = String.format("A null parameter of %s detected", parameterTypes[i]);
                if (log.isTraceEnabled()) {
                    log.trace("message -> {}", message);
                }
                messages.add(message);
            } else {
                // TODO
            }
        });
        return messages;
    }
}
