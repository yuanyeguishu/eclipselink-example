/**
 * Copyright © 2015-2017 Masamitsu Namioka (masamitsunamioka@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.interceptor.InvocationContext;

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
