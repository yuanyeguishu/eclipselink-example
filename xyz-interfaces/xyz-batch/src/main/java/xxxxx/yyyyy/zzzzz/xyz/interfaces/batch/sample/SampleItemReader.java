package xxxxx.yyyyy.zzzzz.xyz.interfaces.batch.sample;

import java.io.Serializable;
import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@lombok.extern.slf4j.Slf4j
@Dependent
@Named
public class SampleItemReader implements ItemReader {

    @Override
    public void open(Serializable checkpoint) throws Exception {
        log.info("#open {}", getClass().getName());
    }

    @Override
    public void close() throws Exception {
        log.info("#close {}", getClass().getName());
    }

    @Override
    public Object readItem() throws Exception {
        log.info("#readItem {}", getClass().getName());
        return null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        log.info("#checkpointInfo {}", getClass().getName());
        return null;
    }
}
