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
package xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@lombok.extern.slf4j.Slf4j

@Dependent
@Interceptor @TraceBeanLifecycle
public class TraceBeanLifecycleInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TraceBeanLifecycleInterceptor.class); // FIXME issues/#92

    @AroundConstruct
    Object aroundConstruct(InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } finally {
            if (log.isTraceEnabled()) {
                log.trace("[Lifecycle][@AroundConstruct] {}", ctx.getTarget().toString());
            }
        }
    }

    @PostConstruct
    void postConstruct(InvocationContext ctx) {
        try {
            ctx.proceed();
            if (log.isTraceEnabled()) {
                log.trace("[Lifecycle][@PostConstruct]   {}", ctx.getTarget().toString());
            }
        } catch (Exception cause) {
            throw new RuntimeException(cause);
        }
    }

    @PreDestroy
    void preDestroy(InvocationContext ctx) {
        try {
            if (log.isTraceEnabled()) {
                log.trace("[Lifecycle][@PreDestroy]      {}", ctx.getTarget().toString());
            }
            ctx.proceed();
        } catch (Exception cause) {
            throw new RuntimeException(cause);
        }
    }
}
