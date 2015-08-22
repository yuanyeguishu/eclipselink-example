package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.domain.model.Sample_;
import xxxxx.yyyyy.zzzzz.persistence.jpa.AbstractRepository;

@lombok.extern.slf4j.Slf4j
@ApplicationScoped
public class SampleRepositoryImpl extends AbstractRepository<Sample, Long> implements SampleRepository {

    @Override
    public List<Sample> findAll() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Sample> cq = cb.createQuery(entityClass);
////        Root<Sample> r = cq.from(entityClass);
//        cq.select(cq.from(entityClass));
////                .where(null)
////                .having(null)
////                .orderBy(null)
////                .groupBy(null);
//        return entityManager.createQuery(cq).resultList();
        return super.resultList((b, q, r) -> q.select(r));
    }

    @Override
    public Sample findByName(String name) {
        return super.singleResult((b, q, r) -> q.select(r).where(b.equal(r.get(Sample_.name), name)));
    }
}
