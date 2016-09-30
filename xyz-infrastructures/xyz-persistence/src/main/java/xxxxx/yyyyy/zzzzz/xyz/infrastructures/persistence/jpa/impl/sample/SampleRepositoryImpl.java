package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.impl.sample;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.SampleRepository;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.AbstractRepository;

@lombok.extern.slf4j.Slf4j
//@Typed(value = SampleRepository.class)
@javax.enterprise.context.ApplicationScoped
//@javax.ejb.Singleton
//@javax.ejb.Stateful
//@javax.ejb.Stateless
public class SampleRepositoryImpl extends AbstractRepository<Sample, Long> implements SampleRepository {

    @Inject
    public SampleRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
//        if (log.isTraceEnabled()) {
//            log.trace(String.format("Constructor this.entityManager.toString()=%s",
//                    this.entityManager.toString()));
//        }
    }
//
//    @PostConstruct
//    void postConstruct() {
//        if (log.isTraceEnabled()) {
//            log.trace(String.format("@PostConstruct this.entityManager.toString()=%s",
//                    this.entityManager.toString()));
//        }
//    }

    @Override
    public List<Sample> findAll() {
        if (log.isDebugEnabled()) {
            log.trace(String.format("%s -> %s", "findAll", this.entityManager.toString()));
        }
//        return resultList(entityManager, entityClass, (b, q, r) -> {
//            return q.select(r);
//        });
        final String jpql = "select x from Sample as x";
        return this.entityManager
                .createQuery(jpql, Sample.class)
                .getResultList();
    }

    //@Valid(ignore = true)
    @Override
    public Sample findByName(String name) {
//        return singleResult(entityManager, entityClass, (b, q, r) -> {
//            return q.select(r).where(b.equal(r.get(Sample_.name), name));
//        });
        final String jpql = "select x from Sample as x where x.name = :name";
        return this.entityManager
                .createQuery(jpql, Sample.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
