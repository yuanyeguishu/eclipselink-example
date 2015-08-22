//package xxxxx.yyyyy.zzzzz.persistence.jpa;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//@lombok.extern.slf4j.Slf4j
////@ApplicationScoped
//public class ApplicationManagedEntityManagerFactoryProducer {
//
//    private static final String PERSISTENCE_UNIT_NAME = "test_PU"; // TODO inject
//
//    @ApplicationScoped
//    @Produces
//    public EntityManagerFactory produce() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//        if (log.isDebugEnabled()) {
//            log.debug(String.format("produce -> %s", emf.toString()));
//        }
//        return emf;
//    }
//
//    public void dispose(final @Disposes EntityManagerFactory emf) {
//        if (emf != null && emf.isOpen()) {
//            emf.close();
//        }
//    }
//}
