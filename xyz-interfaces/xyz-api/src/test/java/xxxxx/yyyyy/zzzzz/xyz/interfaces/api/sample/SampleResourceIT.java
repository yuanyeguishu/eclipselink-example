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
package xxxxx.yyyyy.zzzzz.xyz.interfaces.api.sample;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;
import java.util.stream.Stream;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import xxxxx.yyyyy.zzzzz.xyz.interfaces.api.Application;
import xxxxx.yyyyy.zzzzz.xyz.interfaces.api.POM;

@lombok.extern.slf4j.Slf4j
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class SampleResourceIT {

    @Rule
    public TestName name = new TestName();
    @Resource //@Inject
    private UserTransaction userTransaction;
    @PersistenceContext //@Inject
    private EntityManager entityManager;
    @Inject
    private SampleResource sampleResource;
    @ArquillianResource
    private URL url;

    @Deployment
    public static WebArchive createDeployment() throws Exception {
        try {
            String artifactId = POM.getArtifactId();
            String version = POM.getVersion();
            WebArchive war = ShrinkWrap.create(WebArchive.class)//, artifactId + ".war")
                    .addPackages(true, "xxxxx.yyyyy.zzzzz.xyz.interfaces.api")
                    .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                    .addAsResource("logback.xml", "logback.xml")
                    .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                    .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
            File[] files = Maven.resolver()
                    .loadPomFromFile("pom.xml")
                    .importRuntimeDependencies()
                    .resolve()
                    .withTransitivity()
                    .asFile();
            Stream.of(files).forEach(f -> war.addAsLibrary(f, POM.resolveVersion(f.getName(), version)));
            if (log.isDebugEnabled()) {
                log.debug(war.toString(true));
            }
            return war;
        } catch (Exception cause) {
            cause.printStackTrace();
            throw cause;
        }
    }

    @Before
    public void before() throws Exception {
        log.info(String.format("\n\n\n[START] %s", name.getMethodName()));
        this.begin();
        //String jpql = String.format("delete from %s", Sample.class.getName());
        this.entityManager.createQuery("delete from Sample").executeUpdate();
        this.commit();
    }

    @After
    public void after() throws Exception {
        log.info("[END]\n\n\n");
    }

    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior0() throws Exception { // TODO should change method name
        if (log.isTraceEnabled()) {
            log.trace(String.format("url=%s", url.toString()));
        }
        String applicationPath = Application.class.getAnnotation(ApplicationPath.class).value().trim().replaceFirst("^/", "");
        if (log.isTraceEnabled()) {
            log.trace(String.format("applicationPath=%s", applicationPath));
        }
        try {
            // 1st request.
            ClientBuilder.newClient()
                    .target(this.url.toURI())
                    .path(applicationPath).path("samples")
                    //.request(MediaType.APPLICATION_JSON)
                    .request()
                    .post(Entity.json(
                            Json.createObjectBuilder()
                                    .add("id", 1L)
                                    //.add("version", 0L)
                                    .add("name", UUID.randomUUID().toString())
                                    .build()
                    ));
            JsonArray jsonArray1
                    = ClientBuilder.newClient()
                            .target(this.url.toURI())
                            .path(applicationPath).path("samples")
                            //.request(MediaType.APPLICATION_JSON)
                            .request()
                            .accept(MediaType.APPLICATION_JSON)
                            .get(JsonArray.class);
            if (log.isTraceEnabled()) {
                log.trace(String.format("jsonArray1=%s", jsonArray1.toString()));
            }
            assertThat("", jsonArray1.size(), equalTo(1));
            // 2nd request.
            ClientBuilder.newClient()
                    .target(this.url.toURI())
                    .path(applicationPath).path("samples")
                    //.request(MediaType.APPLICATION_JSON)
                    .request()
                    .post(Entity.json(
                            Json.createObjectBuilder()
                                    .add("id", 2L)
                                    //.add("version", 0L)
                                    .add("name", UUID.randomUUID().toString())
                                    .build()
                    ));
            JsonArray jsonArray2
                    = ClientBuilder.newClient()
                            .target(this.url.toURI())
                            .path(applicationPath).path("samples")
                            //.request(MediaType.APPLICATION_JSON)
                            .request()
                            .accept(MediaType.APPLICATION_JSON)
                            .get(JsonArray.class);
            if (log.isTraceEnabled()) {
                log.trace(String.format("jsonArray2=%s", jsonArray2.toString()));
            }
            assertThat("", jsonArray2.size(), equalTo(2));
        } catch (URISyntaxException cause) {
            throw new RuntimeException(cause);
        }
    }

    private void begin() {
        try {
            this.userTransaction.begin();
            this.entityManager.joinTransaction();
        } catch (NotSupportedException | SystemException cause) {
            throw new RuntimeException(cause);
        } finally {
        }
    }

    private void commit() {
        try {
            // TODO
            if (this.userTransaction.getStatus() != Status.STATUS_ACTIVE) {
                throw new IllegalStateException(String.format("Illegal status=%d", this.userTransaction.getStatus()));
            }
            this.userTransaction.commit();
            this.entityManager.clear();
            this.entityManager.getEntityManagerFactory().getCache().evictAll();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException cause) {
            throw new RuntimeException(cause);
        } finally {
        }
    }

    private void rollback() {
        try {
            // TODO
            this.userTransaction.rollback();
            this.entityManager.clear();
            this.entityManager.getEntityManagerFactory().getCache().evictAll();
        } catch (SystemException cause) {
            throw new RuntimeException(cause);
        } finally {
        }
    }
}
