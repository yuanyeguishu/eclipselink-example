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

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@lombok.extern.slf4j.Slf4j

@Dependent
@Named
public class SampleItemProcessor implements ItemProcessor {

    private static final Logger log = LoggerFactory.getLogger(SampleItemProcessor.class); // FIXME issues/#92

    @Override
    public Object processItem(Object item) throws Exception {
        log.info("#processItem {}", getClass().getName());
        return null;
    }
}
