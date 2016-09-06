package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@lombok.extern.slf4j.Slf4j
@RequestScoped
public class ContainerManagedEntityManagerProducer {

    private static final String PERSISTENCE_UNIT_NAME = "xyz_PU"; // TODO inject
    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager em;

    @RequestScoped
    @Produces
    public EntityManager produce() {
        if (log.isTraceEnabled()) {
            log.trace(String.format("produce -> %s", em.toString()));
        }
        return em;
    }

    public void dispose(@Disposes EntityManager em) {
        if (log.isTraceEnabled()) {
            log.trace(String.format("dispose -> %s", em.toString()));
        }
// Only an application-managed entity manager...
// @see javax.persistence.EntityManager.close()
//        if (em != null && em.isOpen()) {
//            em.close();
//        }
    }
}
