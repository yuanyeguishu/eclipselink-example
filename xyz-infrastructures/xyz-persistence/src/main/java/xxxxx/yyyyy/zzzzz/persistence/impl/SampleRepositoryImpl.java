package xxxxx.yyyyy.zzzzz.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;

public class SampleRepositoryImpl implements SampleRepository {

    @Override
    public List<Sample> findAll() {
        List<Sample> samples = new ArrayList<>();
        Stream.iterate(0L, i -> i + 1).limit(10L).forEach(i -> {
            Sample sample = new Sample();
            sample.setId(i);
            samples.add(sample);
        });
        return samples;
    }
}
