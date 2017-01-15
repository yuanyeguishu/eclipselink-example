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
package xxxxx.yyyyy.zzzzz.xyz.interfaces.batch.sample;

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
    public void open(Serializable checkpoint) throws Exception {
        log.info("#open {}", getClass().getName());
    }

    @Override
    public void close() throws Exception {
        log.info("#close {}", getClass().getName());
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        log.info("#writeItems {}", getClass().getName());
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        log.info("#checkpointInfo {}", getClass().getName());
        return null;
    }
}
