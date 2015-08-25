package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@Inherited
@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface RepositoryValidation {

    @Nonbinding
    boolean flagA() default true;

    @Nonbinding
    boolean flagB() default true;
}
