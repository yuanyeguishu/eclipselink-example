package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.Sample_;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.AbstractRepository;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.Functional;

@lombok.extern.slf4j.Slf4j
//@Typed(value = SampleRepository.class)
@ApplicationScoped
public class SampleRepositoryImpl extends AbstractRepository<Sample, Long> implements SampleRepository, Functional<Sample, Long> {

    @Inject
    public SampleRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Sample> findAll() {
        return resultList(entityManager, entityClass, (b, q, r) -> {
            return q.select(r);
        });
    }

    //@Valid(ignore = true)
    @Override
    public Sample findByName(String name) {
        return singleResult(entityManager, entityClass, (b, q, r) -> {
            return q.select(r).where(b.equal(r.get(Sample_.name), name));
        });
    }

    @Override
    public void bulkUpdateNameByIds(String name, List<Long> ids) {
        // UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = Hoge WHERE (ID IN (1, 2))
        bulkUpdate(entityManager, entityClass, (b, u, r) -> {
            return u.set(Sample_.name, name).where(r.get(Sample_.id).in(ids));
        });
    }

    @Override
    public void bulkDeleteByIds(List<Long> ids) {
        // DELETE FROM SAMPLE WHERE (ID IN (?, ?))
        bulkDelete(entityManager, entityClass, (b, d, r) -> {
            return d.where(r.get(Sample_.id).in(ids));
        });
    }
}
