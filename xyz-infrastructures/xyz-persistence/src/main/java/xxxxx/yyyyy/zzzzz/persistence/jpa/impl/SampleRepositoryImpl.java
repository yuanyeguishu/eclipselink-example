package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.domain.model.Sample_;
import xxxxx.yyyyy.zzzzz.persistence.jpa.AbstractRepository;
import xxxxx.yyyyy.zzzzz.persistence.jpa.ArgumentsValidation;

@lombok.extern.slf4j.Slf4j
@ApplicationScoped
public class SampleRepositoryImpl extends AbstractRepository<Sample, Long> implements SampleRepository {

    @Override
    public List<Sample> findAll() {
        return super.resultList((b, q, r) -> q.select(r));
    }

    @ArgumentsValidation(flagA = false, flagB = false)
    @Override
    public Sample findByName(String name) {
        return super.singleResult((b, q, r) -> q.select(r).where(b.equal(r.get(Sample_.name), name)));
    }

    @Override
    public void bulkUpdateNameByIds(String name, List<Long> ids) {
//        // UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = Hoge WHERE (ID IN (1, 2))
//        super.bulkUpdate((b, u, r) -> u.set(Sample_.name, name).where(r.get(Sample_.id).in(ids)));
        throw new UnsupportedOperationException();
    }

    @Override
    public void bulkDeleteByIds(List<Long> ids) {
//        // DELETE FROM SAMPLE WHERE (ID IN (?, ?))
//        super.bulkDelete((b, d, r) -> d.where(r.get(Sample_.id).in(ids)));
        throw new UnsupportedOperationException();
    }
}
