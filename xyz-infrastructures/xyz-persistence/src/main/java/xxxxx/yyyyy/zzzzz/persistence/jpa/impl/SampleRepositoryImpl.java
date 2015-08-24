package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.domain.model.Sample_;
import xxxxx.yyyyy.zzzzz.persistence.jpa.AbstractRepository;
import xxxxx.yyyyy.zzzzz.persistence.jpa.ArgumentsValidation;

@lombok.extern.slf4j.Slf4j
@ArgumentsValidation
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

    @ArgumentsValidation(flagA = false, flagB = false)
    @Override
    public Sample findByName(String name) {
        return super.singleResult((b, q, r) -> q.select(r).where(b.equal(r.get(Sample_.name), name)));
    }

    @Override
    public void updateNameByIds(String name, List<Long> ids) {
        // 2015-08-23 12:14:15.398 [http-listener(2)] DEBUG o.e.p.s.f.0.0.jar_xyz_PU.sql - UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = Hoge WHERE (ID IN (1, 2))
        super.directUpdate((b, u, r) -> u.set(Sample_.name, name).where(r.get(Sample_.id).in(ids)));
    }

    @Override
    public void removeByIds(List<Long> ids) {
        // 2015-08-23 12:14:15.577 [http-listener(4)] DEBUG o.e.p.s.f.0.0.jar_xyz_PU.sql - DELETE FROM SAMPLE WHERE (ID IN (?, ?))
        super.directDelete((b, d, r) -> d.where(r.get(Sample_.id).in(ids)));
    }
}
