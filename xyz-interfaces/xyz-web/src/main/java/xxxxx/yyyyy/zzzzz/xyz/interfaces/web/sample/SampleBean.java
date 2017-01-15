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
package xxxxx.yyyyy.zzzzz.xyz.interfaces.web.sample;

import static java.util.stream.Collectors.*;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import xxxxx.yyyyy.zzzzz.xyz.application.sample.service.SampleService;

@lombok.extern.slf4j.Slf4j
@lombok.Data
@Named
@RequestScoped
public class SampleBean {

    @Inject
    private SampleService sampleService;

    public List<String> getSampleNames() {
        return sampleService.service().stream().map(x -> x.name()).collect(toList());
    }
}
