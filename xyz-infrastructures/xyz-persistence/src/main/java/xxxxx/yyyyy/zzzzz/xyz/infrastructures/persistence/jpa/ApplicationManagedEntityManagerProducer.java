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

@lombok.extern.slf4j.Slf4j
@Startup
@Singleton
@Lock(LockType.READ) //TODO
public class ApplicationManagedEntityManagerProducer {

    @PersistenceUnit //(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManagerFactory emf;

    @PostConstruct
    void postConstruct() {
    }

    @PreDestroy
    void preDestroy() {
    }

    @RequestScoped
    @Produces
    public EntityManager produce() {
        //EntityManager em = EntityManagerProxy.newProxyInstance(this.emf.createEntityManager());
        EntityManager em = this.emf.createEntityManager();
        if (log.isTraceEnabled()) {
            log.trace(String.format("@Produces -> %s", em.toString()));
        }
        return em;
    }

    public void dispose(@Disposes EntityManager em) {
        if (log.isTraceEnabled()) {
            log.trace(String.format("@Disposes -> %s", em.toString()));
        }
        if (em.isOpen()) {
            if (log.isTraceEnabled()) {
                log.trace(String.format("@Disposes.close -> %s", em.toString()));
            }
            em.close();
        }
    }
//
//    private static class EntityManagerProxy implements InvocationHandler {
//        private final EntityManager em;
//        public EntityManagerProxy(EntityManager em) {
//            this.em = em;
//        }
//        public static EntityManager newProxyInstance(EntityManager em) {
//            return (EntityManager) Proxy.newProxyInstance(
//                    em.getClass().getClassLoader(), new Class<?>[]{EntityManager.class}, new EntityManagerProxy(em));
//        }
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            em.joinTransaction();
//            return method.invoke(em, args);
//        }
//    }
}
