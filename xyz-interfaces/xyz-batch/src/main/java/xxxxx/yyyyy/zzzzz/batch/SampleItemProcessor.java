package xxxxx.yyyyy.zzzzz.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@lombok.extern.slf4j.Slf4j
@Dependent
@Named
public class SampleItemProcessor implements ItemProcessor {

    @Override
    public Object processItem(final Object item) throws Exception {
        log.info("#processItem {}", getClass().getName());
        return null;
    }
}
