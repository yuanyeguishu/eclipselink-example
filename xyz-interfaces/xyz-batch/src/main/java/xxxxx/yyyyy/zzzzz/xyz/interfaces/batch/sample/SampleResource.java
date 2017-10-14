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

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@lombok.extern.slf4j.Slf4j

@Path("sample")
@RequestScoped
public class SampleResource {

    private static final Logger log = LoggerFactory.getLogger(SampleResource.class); // FIXME issues/#92
    private static final String JOB_XML_NAME = "sample-job";

    @GET
    public String start(/*@QueryParam("jobXMLName") String jobXMLName*/) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        log.info("start");
        long id = jobOperator.start(JOB_XML_NAME, null);
        log.info("end");
        return String.format("%s -> %d", JOB_XML_NAME, id);
    }
}
