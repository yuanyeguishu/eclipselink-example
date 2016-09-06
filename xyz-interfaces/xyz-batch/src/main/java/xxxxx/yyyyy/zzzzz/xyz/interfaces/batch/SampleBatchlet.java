package xxxxx.yyyyy.zzzzz.xyz.interfaces.batch;

import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchStatus;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@lombok.extern.slf4j.Slf4j
@Dependent
@Named
public class SampleBatchlet implements Batchlet {

    @Override
    public String process() throws Exception {
        log.info("#process {}", getClass().getName());
        return BatchStatus.COMPLETED.toString();
    }

    @Override
    public void stop() throws Exception {
        log.info("#stop {}", getClass().getName());
    }
}
