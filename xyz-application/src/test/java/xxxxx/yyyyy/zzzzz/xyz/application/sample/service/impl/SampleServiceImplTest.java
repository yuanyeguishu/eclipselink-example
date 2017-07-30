/**
 * Copyright © 2015-2017 Masamitsu Namioka (masamitsunamioka@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
