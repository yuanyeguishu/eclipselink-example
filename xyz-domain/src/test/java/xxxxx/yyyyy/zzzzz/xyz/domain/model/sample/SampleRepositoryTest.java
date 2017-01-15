/**
 * Copyright © 2015 Masamitsu Namioka (masamitsunamioka@gmail.com)
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
package xxxxx.yyyyy.zzzzz.xyz.domain.model.sample;

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
        when(sampleRepository.findAll()).thenReturn(testData(5));
        assertThat("", sampleRepository.findAll().size(), is(5));
    }

    private List<Sample> testData(long size) {
        List<Sample> samples = new ArrayList<>();
        Stream.iterate(0L, i -> i + 1).limit(size).forEach(i -> {
//            Sample sample = new Sample();
//            sample.setId(i);
//            samples.add(sample);
            samples.add(new Sample(i, "Name" + i));
        });
        return samples;
    }
}
