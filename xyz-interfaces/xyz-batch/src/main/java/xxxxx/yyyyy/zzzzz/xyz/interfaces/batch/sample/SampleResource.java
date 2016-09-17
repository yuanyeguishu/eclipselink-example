package xxxxx.yyyyy.zzzzz.xyz.interfaces.batch.sample;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@lombok.extern.slf4j.Slf4j
@Path("sample")
@RequestScoped
public class SampleResource {

    private static final String JOB_XML_NAME = "sample-job";

    @GET
    public String start(/*@QueryParam("jobXMLName") String jobXMLName*/) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        log.info("start");
        long id = jobOperator.start(JOB_XML_NAME, null);
        log.info("end");
        return String.format("%s -> %d", JOB_XML_NAME, id);
    }
}
