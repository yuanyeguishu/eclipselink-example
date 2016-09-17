package xxxxx.yyyyy.zzzzz.xyz.application.sample.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.SampleRepository;

@Slf4j
public class SampleServiceImplTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private SampleRepository sampleRepository;
    @InjectMocks
    private SampleServiceImpl sampleServiceImpl;

    @Before
    public void before() {
//        sampleServiceImpl = new SampleServiceImpl();
//        sampleRepository = mock(SampleRepository.class);
//        inject(sampleServiceImpl, getOriginalClassOf(sampleRepository), sampleRepository);
    }

    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior() { // TODO should change method name
        when(sampleRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        List<Sample> samples = sampleServiceImpl.service();
        assertThat("", samples.size(), is(0));
    }
//    private Class<?> getOriginalClassOf(Object mock) {
//        try {
//            return Class.forName(mock.getClass().getName().replaceAll("\\$\\$EnhancerByMockitoWithCGLIB\\$\\$.*", ""));
//        } catch (ClassNotFoundException cause) {
//            throw new RuntimeException(cause);
//        }
//    }
//
//    private void inject(Object target, Class<?> c, Object o) {
//        Stream.of(target.getClass().getDeclaredFields())
//                .filter(f -> f.getType() == c)
//                .peek(f -> {
//                    if (Modifier.isPrivate(f.getModifiers())) {
//                        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
//                            f.setAccessible(true);
//                            return null;
//                        });
//                    }
//                })
//                .forEach(f -> {
//                    try {
//                        f.set(target, o);
//                    } catch (IllegalArgumentException | IllegalAccessException cause) {
//                        throw new RuntimeException(cause);
//                    }
//                });
//    }
}
