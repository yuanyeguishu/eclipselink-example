package xxxxx.yyyyy.zzzzz.domain.model;

import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.Repository;

public interface SampleRepository extends Repository<Sample, Long> {

    @Override
    List<Sample> findAll();

    Sample findByName(String name);

    void updateNameByIds(String name, List<Long> ids);

    void removeByIds(List<Long> ids);
}
