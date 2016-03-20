package xxxxx.yyyyy.zzzzz.application.service.impl;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import xxxxx.yyyyy.zzzzz.application.service.SampleService;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;

@lombok.extern.slf4j.Slf4j
@ApplicationScoped
public class SampleServiceImpl implements SampleService {

    @Inject
    private SampleRepository sampleRepository;

    @Override
    public List<Sample> service() {
        List<Sample> samples = sampleRepository.findAll();
        log.info("samples.size={}", samples.size());
        java.util.logging.Logger.getLogger(getClass().getName()).info("samples.size=" + String.valueOf(samples.size()));
        return samples;
        //return sampleRepository.findAll();
    }
}
