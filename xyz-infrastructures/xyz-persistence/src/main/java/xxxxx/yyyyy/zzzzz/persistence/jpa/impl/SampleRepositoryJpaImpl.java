package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.persistence.jpa.AbstractRepositoryJpa;

@lombok.extern.slf4j.Slf4j
//@Typed(value = SampleRepository.class)
@ApplicationScoped
public class SampleRepositoryJpaImpl extends AbstractRepositoryJpa<Sample, Long> implements SampleRepository/*, Functional<Sample, Long>*/ {

    @Inject
    public SampleRepositoryJpaImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Sample> findAll() {
//        return resultList((b, q, r) -> q.select(r));
        //String jpql = "select x from Sample as x order by x.name desc, x.id asc";
        String jpql = "select x from Sample as x";
        return this.entityManager
                .createQuery(jpql, this.entityClass)
                .getResultList();
    }

    //@Valid(ignore = true)
    @Override
    public Sample findByName(String name) {
//        return singleResult((b, q, r)
//                -> q.select(r).where(b.equal(r.get(Sample_.name), name)));
        String jpql = "select x from Sample as x where x.name = :name";
        return this.entityManager
                .createQuery(jpql, this.entityClass)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void bulkUpdateNameByIds(String name, List<Long> ids) {
//        // UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = Hoge WHERE (ID IN (1, 2))
//        bulkUpdate((b, u, r)
//                -> u.set(Sample_.name, name).where(r.get(Sample_.id).in(ids)));
        String jpql = "update Sample as x set x.version = (x.version + 1), x.name = :name where x.id in :ids";
        this.entityManager
                .createQuery(jpql)
                .setParameter("name", name)
                .setParameter("ids", ids)
                .executeUpdate();
    }

    @Override
    public void bulkDeleteByIds(List<Long> ids) {
//        // DELETE FROM SAMPLE WHERE (ID IN (?, ?))
//        bulkDelete((b, d, r)
//                -> d.where(r.get(Sample_.id).in(ids)));
        String jpql = "delete from Sample as x where x.id in :ids";
        this.entityManager
                .createQuery(jpql)
                .setParameter("ids", ids)
                .executeUpdate();
    }
}
