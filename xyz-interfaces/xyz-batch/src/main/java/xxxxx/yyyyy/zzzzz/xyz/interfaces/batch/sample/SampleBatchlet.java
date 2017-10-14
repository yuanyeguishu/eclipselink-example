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
package xxxxx.yyyyy.zzzzz.xyz.interfaces.batch.sample;

import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchStatus;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@lombok.extern.slf4j.Slf4j

@Dependent
@Named
public class SampleBatchlet implements Batchlet {

    private static final Logger log = LoggerFactory.getLogger(SampleBatchlet.class); // FIXME issues/#92

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
