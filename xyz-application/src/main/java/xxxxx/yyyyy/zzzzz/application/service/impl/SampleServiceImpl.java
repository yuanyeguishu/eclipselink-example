package xxxxx.yyyyy.zzzzz.application.service.impl;

import java.util.List;
import xxxxx.yyyyy.zzzzz.application.service.SampleService;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;

public class SampleServiceImpl implements SampleService {

    private SampleRepository sampleRepository;

    @Override
    public List<Sample> service() {
        return sampleRepository.findAll();
    }
}
