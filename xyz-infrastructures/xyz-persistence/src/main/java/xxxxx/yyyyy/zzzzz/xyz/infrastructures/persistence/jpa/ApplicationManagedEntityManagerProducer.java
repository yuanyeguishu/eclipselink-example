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
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental.TraceBeanLifecycle;
//@lombok.extern.slf4j.Slf4j

@TraceBeanLifecycle
@Startup
@Singleton
@Lock(LockType.READ) //TODO
public class ApplicationManagedEntityManagerProducer {

    private static final Logger log = LoggerFactory.getLogger(ApplicationManagedEntityManagerProducer.class); // FIXME issues/#92
    @PersistenceUnit //(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    void postConstruct() {
    }

    @PreDestroy
    void preDestroy() {
        // if (this.entityManagerFactory.isOpen()) {
        //     // java.lang.IllegalStateException: Attempting to execute an operation on a closed EntityManagerFactory.
        //     this.entityManagerFactory.close();
        // }
    }

    @RequestScoped
    @Produces
    public EntityManager produce() {
        //EntityManager entityManager = EntityManagerProxy.newProxyInstance(this.entityManagerFactory.createEntityManager());
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        if (log.isTraceEnabled()) {
            log.trace(String.format("[Lifecycle][@Produces] entityManager=%s", entityManager.toString()));
        }
        return entityManager;
    }

    public void dispose(@Disposes EntityManager entityManager) {
        if (log.isTraceEnabled()) {
            log.trace(String.format("[Lifecycle][@Disposes] entityManager=%s", entityManager.toString()));
        }
        if (entityManager.isOpen()) {
            entityManager.close();
            if (log.isTraceEnabled()) {
                log.trace(String.format("[Lifecycle][@Disposes.closed] entityManager=%s", entityManager.toString()));
            }
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString())
                .append(" {")
                .append("this.entityManagerFactory=").append((this.entityManagerFactory == null) ? "null" : this.entityManagerFactory.toString())
                //.append(", ")
                .append("}")
                .toString();
    }
//
//    private static class EntityManagerProxy implements InvocationHandler {
//        private final EntityManager entityManager;
//        public EntityManagerProxy(EntityManager o) {
//            this.entityManager = o;
//        }
//        public static EntityManager newProxyInstance(EntityManager o) {
//            return (EntityManager) Proxy.newProxyInstance(
//                    o.getClass().getClassLoader(), new Class<?>[]{EntityManager.class}, new EntityManagerProxy(o));
//        }
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            this.entityManager.joinTransaction();
//            return method.invoke(this.entityManager, args);
//        }
//    }
}
