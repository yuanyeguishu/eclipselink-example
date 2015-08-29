package xxxxx.yyyyy.zzzzz.persistence.jpa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@lombok.extern.slf4j.Slf4j
//@ApplicationScoped
public class ApplicationManagedEntityManagerProducer {

    @Inject
    private EntityManagerFactory emf;

    @Dependent
    @Produces
    public EntityManager produce() {
        EntityManager em = EntityManagerProxy.newProxyInstance(emf.createEntityManager());
        if (log.isTraceEnabled()) {
            log.trace(String.format("produce -> %s", em.toString()));
        }
        return em;
    }

    public void dispose(final @Disposes EntityManager em) {
        if (log.isTraceEnabled()) {
            log.trace(String.format("dispose -> %s", em.toString()));
        }
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    private static class EntityManagerProxy implements InvocationHandler {

        private final EntityManager em;

        public EntityManagerProxy(final EntityManager em) {
            this.em = em;
        }

        public static EntityManager newProxyInstance(final EntityManager em) {
            return (EntityManager) Proxy.newProxyInstance(
                    em.getClass().getClassLoader(), new Class<?>[]{EntityManager.class}, new EntityManagerProxy(em));
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            em.joinTransaction();
            return method.invoke(em, args);
        }
    }
}
