package xxxxx.yyyyy.zzzzz.xyz.application.sample.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import xxxxx.yyyyy.zzzzz.xyz.application.sample.service.SampleService;
import xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental.TraceBeanLifecycle;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.SampleRepository;

@lombok.extern.slf4j.Slf4j
@TraceBeanLifecycle
@javax.enterprise.context.ApplicationScoped
//@javax.ejb.Stateless
public class SampleServiceImpl implements SampleService {

    @Inject
    private SampleRepository sampleRepository;

    @PostConstruct
    void postConstruct() {
    }

    @PreDestroy
    void preDestroy() {
    }

    @Override
    public List<Sample> service() {
        List<Sample> samples = this.sampleRepository.findAll();
        log.info(String.format("samples.size()=%d", samples.size()));
        java.util.logging.Logger.getLogger(getClass().getName()).info(String.format("samples.size()=%d", samples.size()));
        return samples;
    }

    @Transactional
    @Override
    public void create(Sample sample) {
        this.sampleRepository.store(sample);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString())
                .append(" {")
                .append("this.sampleRepository=").append((this.sampleRepository == null) ? "null" : this.sampleRepository.toString())
                //.append(", ")
                .append("}")
                .toString();
    }
}
