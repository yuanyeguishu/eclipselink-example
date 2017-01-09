package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa;

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

@lombok.extern.slf4j.Slf4j
@Startup
@Singleton
@Lock(LockType.READ) //TODO
public class ApplicationManagedEntityManagerProducer {

    @PersistenceUnit //(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManagerFactory entityManagerFactory;
//
//    @PostConstruct
//    void postConstruct() {
//    }
//
//    @PreDestroy
//    void preDestroy() {
//    }

    @RequestScoped
    @Produces
    EntityManager produce() {
        //EntityManager entityManager = EntityManagerProxy.newProxyInstance(this.entityManagerFactory.createEntityManager());
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        if (log.isTraceEnabled()) {
            log.trace(String.format("@Produces -> %s", entityManager.toString()));
        }
        return entityManager;
    }

    void dispose(@Disposes EntityManager entityManager) {
        if (log.isTraceEnabled()) {
            log.trace(String.format("@Disposes -> %s", entityManager.toString()));
        }
        if (entityManager.isOpen()) {
            entityManager.close();
            if (log.isTraceEnabled()) {
                log.trace(String.format("@Disposes.closed -> %s", entityManager.toString()));
            }
        }
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
