package xxxxx.yyyyy.zzzzz.xyz.domain.model.sample;

import java.util.List;
import xxxxx.yyyyy.zzzzz.xyz.domain.shared.Repository;

public interface SampleRepository extends Repository<Sample, Long> {

    @Override
    List<Sample> findAll();

    Sample findByName(String name);
}
