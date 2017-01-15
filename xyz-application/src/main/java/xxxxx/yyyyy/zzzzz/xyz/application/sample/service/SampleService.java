package xxxxx.yyyyy.zzzzz.xyz.application.sample.service;

import java.util.List;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;

public interface SampleService {

    List<Sample> service();

    void create(Sample sample);
}
