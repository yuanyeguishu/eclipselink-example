package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

@Inherited
@InterceptorBinding
@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface Trace {
}
