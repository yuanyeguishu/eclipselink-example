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
package xxxxx.yyyyy.zzzzz.xyz.interfaces.api.sample;

import java.io.StringReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import xxxxx.yyyyy.zzzzz.xyz.application.sample.service.SampleService;
import xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental.TraceBeanLifecycle;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;

@lombok.extern.slf4j.Slf4j
@TraceBeanLifecycle
@Path("samples")
@RequestScoped
public class SampleResource {

    @Inject
    private SampleService sampleService;
    @Context
    private UriInfo uriInfo;

    @PostConstruct
    void postConstruct() {
    }

    @PreDestroy
    void preDestroy() {
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public JsonArray get() {
        return this.sampleService.service().stream()
                .map(x -> {
                    return Json.createObjectBuilder()
                            .add("id", x.id())
                            .add("version", x.version())
                            .add("name", x.name())
                            .build();
                })
                .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add)
                .build();
    }

    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public Response post(String json) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(json));) {
            JsonObject jsonObject = jsonReader.readObject();
            Long id = jsonObject.getJsonNumber("id").longValueExact();
            Sample sample = new Sample(
                    id,
                    //jsonObject.getJsonNumber("version").longValueExact(),
                    jsonObject.getJsonString("name").getString());
            this.sampleService.create(sample);
            UriBuilder builder = this.uriInfo.getAbsolutePathBuilder();
            builder.path(Long.toString(id));
            return Response.created(builder.build()).build();
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString())
                .append(" {")
                .append("this.sampleService=").append((this.sampleService == null) ? "null" : this.sampleService.toString())
                .append(", ")
                .append("this.uriInfo=").append((this.uriInfo == null) ? "null" : this.uriInfo.toString())
                .append("}")
                .toString();
    }
}
