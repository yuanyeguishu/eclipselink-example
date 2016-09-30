package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.impl.sample;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.SampleRepository;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.POM;

@lombok.extern.slf4j.Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class SampleRepositoryImplIT {

    @Rule
    public TestName name = new TestName();
    @Inject
    private SampleRepository sampleRepository;

    @Deployment
    public static WebArchive createDeployment() throws Exception {
        String artifactId = POM.getArtifactId();
        String version = POM.getVersion();
        WebArchive war = ShrinkWrap.create(WebArchive.class);
        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();
        Stream.of(files).forEach(f -> war.addAsLibrary(f, POM.resolveVersion(f.getName(), version)));
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, String.format("%s-%s.jar", artifactId, version))
                .addPackages(true, "xxxxx.yyyyy.zzzzz")
                .addAsResource("META-INF/beans.xml", "META-INF/beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("logback.xml", "logback.xml");
        war.addAsLibraries(jar);
        if (log.isDebugEnabled()) {
            log.debug(war.toString(true));
        }
        return war;
    }

    @Before
    public void before() throws Exception {
        log.info(String.format("\n\n\n[START] %s", name.getMethodName()));
    }

    @After
    public void after() throws Exception {
        log.info("[END]\n\n\n");
    }

    //@Transactional
    @Test(expected = IllegalArgumentException.class)
    //@Test(expected = javax.ejb.EJBException.class)
    public void UnitOfWork_StateUnderTest_ExpectedBehavior0() throws Exception { // TODO should change method name
        //this.sampleRepository.findByName(null);
        try {
        this.sampleRepository.findByName(null);
        } catch(Exception cause) {
        cause.printStackTrace();
        throw cause;
        }
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior1() throws Exception { // TODO should change method name
        this.sampleRepository.findAll().stream()
                .forEach(this.sampleRepository::remove);
        List<Sample> samples = this.sampleRepository.findAll();
        assertThat("", samples.size(), equalTo(0));
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior2() throws Exception { // TODO should change method name
        LongStream.range(0L, 10L).boxed()
                .map(x -> new Sample(x, String.valueOf(x)))
                .forEach(this.sampleRepository::store);
        List<Sample> samples = this.sampleRepository.findAll();
        assertThat("", samples.size(), equalTo(10));
        if (log.isTraceEnabled()) {
            samples.stream().forEach(x -> log.trace(x.toString()));
        }
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior3() throws Exception { // TODO should change method name
        Sample sample = this.sampleRepository.findByName("0");
        assertThat("", sample.id(), equalTo(0L));
        assertThat("", sample.version(), equalTo(1L));
        assertThat("", sample.name(), equalTo("0"));
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior4() throws Exception { // TODO should change method name
        this.sampleRepository.findAll().stream()
                .forEach(this.sampleRepository::remove);
        List<Sample> samples = this.sampleRepository.findAll();
        assertThat("", samples.size(), equalTo(0));
    }
}
