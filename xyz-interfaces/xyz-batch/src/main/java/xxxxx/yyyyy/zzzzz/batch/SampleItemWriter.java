package xxxxx.yyyyy.zzzzz.batch;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@lombok.extern.slf4j.Slf4j
@Dependent
@Named
public class SampleItemWriter implements ItemWriter {

    @Override
    public void open(final Serializable checkpoint) throws Exception {
        log.info("#open {}", getClass().getName());
    }

    @Override
    public void close() throws Exception {
        log.info("#close {}", getClass().getName());
    }

    @Override
    public void writeItems(final List<Object> items) throws Exception {
        log.info("#writeItems {}", getClass().getName());
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        log.info("#checkpointInfo {}", getClass().getName());
        return null;
    }
}
