package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.persistence.POM;

@lombok.extern.slf4j.Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class SampleRepositoryImplIT {

    @Inject
    private SampleRepository sampleRepository;

    @Deployment
    public static WebArchive createDeployment() {
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

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior1() { // TODO should change method name
        testData();
        List<Sample> samples = sampleRepository.findAll();
        samples.stream().forEach(x -> {
            if (log.isDebugEnabled()) {
                log.debug(x.toString());
            }
        });
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior2() { // TODO should change method name
        //testData();
        Sample x = sampleRepository.findByName("Name1");
        if (log.isDebugEnabled()) {
            log.debug(x.toString());
        }
    }

    private void testData() {
        for (long x = 1L; x <= 10L; x++) {
            Sample sample = new Sample();
            sample.setId(x);
            sample.setName("Name" + x);
            sampleRepository.store(sample);
        }
    }
}
