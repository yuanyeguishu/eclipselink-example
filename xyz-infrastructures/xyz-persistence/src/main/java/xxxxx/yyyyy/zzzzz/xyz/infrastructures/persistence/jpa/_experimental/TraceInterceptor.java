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

import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@lombok.extern.slf4j.Slf4j

@Dependent
@Interceptor @Trace
public class TraceInterceptor /*extends AbstractInterceptor*/ {

    private static final Logger log = LoggerFactory.getLogger(TraceInterceptor.class); // FIXME issues/#92

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
