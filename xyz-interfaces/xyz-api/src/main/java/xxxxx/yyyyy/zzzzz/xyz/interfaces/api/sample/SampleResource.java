package xxxxx.yyyyy.zzzzz.xyz.interfaces.api.sample;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import xxxxx.yyyyy.zzzzz.xyz.application.sample.service.SampleService;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;

@lombok.extern.slf4j.Slf4j
@Path("sample")
@RequestScoped
public class SampleResource {

    @Inject
    private SampleService sampleService;

    //@Consumes("application/json")
    @GET
    public List<Sample> get() {
        return sampleService.service();
    }
}