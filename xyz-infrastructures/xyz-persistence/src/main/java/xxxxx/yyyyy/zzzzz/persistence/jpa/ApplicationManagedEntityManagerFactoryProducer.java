package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@lombok.extern.slf4j.Slf4j
//@ApplicationScoped
public class ApplicationManagedEntityManagerFactoryProducer {

    private static final String PERSISTENCE_UNIT_NAME = "xyz_PU"; // TODO inject

    @ApplicationScoped
    @Produces
    public EntityManagerFactory produce() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (log.isTraceEnabled()) {
            log.trace(String.format("produce -> %s", emf.toString()));
        }
        return emf;
    }

    public void dispose(@Disposes EntityManagerFactory emf) {
        if (log.isTraceEnabled()) {
            log.trace(String.format("dispose -> %s", emf.toString()));
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
