//package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa;
//
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@lombok.extern.slf4j.Slf4j
//@RequestScoped
//public class ContainerManagedEntityManagerProducer {
//
//    @PersistenceContext //(unitName = PERSISTENCE_UNIT_NAME)
//    private EntityManager entityManager;
//
//    @RequestScoped
//    @Produces
//    public EntityManager produce() {
//        if (log.isTraceEnabled()) {
//            log.trace(String.format("@Produces -> %s", this.entityManager.toString()));
//        }
//        return this.entityManager;
//    }
//
//    public void dispose(@Disposes EntityManager entityManager) {
//        if (log.isTraceEnabled()) {
//            log.trace(String.format("@Disposes -> %s", entityManager.toString()));
//        }
//// Only an application-managed entity manager...
//// @see javax.persistence.EntityManager.close()
////        if (entityManager != null && entityManager.isOpen()) {
////            entityManager.close();
////        }
//    }
//}
