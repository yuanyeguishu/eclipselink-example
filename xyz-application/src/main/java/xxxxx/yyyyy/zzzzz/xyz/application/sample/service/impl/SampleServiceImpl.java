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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxxxx.yyyyy.zzzzz.xyz.application.sample.service.SampleService;
import xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental.TraceBeanLifecycle;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.SampleRepository;
//@lombok.extern.slf4j.Slf4j

@TraceBeanLifecycle
@javax.enterprise.context.ApplicationScoped
//@javax.ejb.Stateless
public class SampleServiceImpl implements SampleService {

    private static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class); // FIXME issues/#92
    @Inject
    private SampleRepository sampleRepository;

    @PostConstruct
    void postConstruct() {
    }

    @PreDestroy
    void preDestroy() {
    }

    @Override
    public List<Sample> service() {
        List<Sample> samples = this.sampleRepository.findAll();
        log.info(String.format("samples.size()=%d", samples.size()));
        java.util.logging.Logger.getLogger(getClass().getName()).info(String.format("samples.size()=%d", samples.size()));
        return samples;
    }

    @Transactional
    @Override
    public void create(Sample sample) {
        this.sampleRepository.store(sample);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString())
                .append(" {")
                .append("this.sampleRepository=").append((this.sampleRepository == null) ? "null" : this.sampleRepository.toString())
                //.append(", ")
                .append("}")
                .toString();
    }
}
