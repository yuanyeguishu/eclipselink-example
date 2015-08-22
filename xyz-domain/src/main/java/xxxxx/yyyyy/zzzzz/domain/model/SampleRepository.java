package xxxxx.yyyyy.zzzzz.domain.model;

import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.Repository;

public interface SampleRepository extends Repository<Sample, Long> {

    List<Sample> findAll();

    Sample findByName(String name);
}
