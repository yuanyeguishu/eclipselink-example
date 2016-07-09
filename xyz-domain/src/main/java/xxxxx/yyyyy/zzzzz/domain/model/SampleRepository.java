package xxxxx.yyyyy.zzzzz.domain.model;

import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.PageableRepository;

public interface SampleRepository extends PageableRepository<Sample, Long> {

    @Override
    List<Sample> findAll();

    Sample findByName(String name);

    void bulkUpdateNameByIds(String name, List<Long> ids);

    void bulkDeleteByIds(List<Long> ids);
}
