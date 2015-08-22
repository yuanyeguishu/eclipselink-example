package xxxxx.yyyyy.zzzzz.domain.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

public class SampleRepositoryTest {

    private SampleRepository sampleRepository;

    @Before
    public void before() {
        sampleRepository = mock(SampleRepository.class);
    }

    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior() { // TODO should change method name
        when(sampleRepository.findAll()).thenReturn(createSamples(5L));
        assertThat("", sampleRepository.findAll().size(), is(5));
    }

    private List<Sample> createSamples(final long size) {
        List<Sample> samples = new ArrayList<>();
        Stream.iterate(0L, i -> i + 1).limit(size).forEach(i -> {
            Sample sample = new Sample();
            sample.setId(i);
            samples.add(sample);
        });
        return samples;
    }
}
