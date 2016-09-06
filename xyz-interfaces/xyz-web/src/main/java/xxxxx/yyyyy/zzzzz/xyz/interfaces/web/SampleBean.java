package xxxxx.yyyyy.zzzzz.xyz.interfaces.web;

import static java.util.stream.Collectors.*;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import xxxxx.yyyyy.zzzzz.xyz.application.service.SampleService;

@lombok.extern.slf4j.Slf4j
@lombok.Data
@Named
@RequestScoped
public class SampleBean {

    @Inject
    private SampleService sampleService;

    public List<String> getSampleNames() {
        return sampleService.service().stream().map(x -> x.name()).collect(toList());
    }
}
