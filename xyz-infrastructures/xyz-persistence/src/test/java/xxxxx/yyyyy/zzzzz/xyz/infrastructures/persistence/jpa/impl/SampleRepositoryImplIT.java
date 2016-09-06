package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.impl;

import static java.util.stream.Collectors.*;

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
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.POM;

@lombok.extern.slf4j.Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class SampleRepositoryImplIT {

    @Inject
    private SampleRepository sampleRepository;
    @Rule
    public TestName name = new TestName();

    @Before
    public void before() {
        log.info(String.format("[START] %s", name.getMethodName()));
    }

    @After
    public void after() {
        log.info("[END]");
    }

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
        //.addAsResource("logging.properties", "logging.properties");
        war.addAsLibraries(jar);
        if (log.isDebugEnabled()) {
            log.debug(war.toString(true));
        }
        return war;
    }

    @Transactional
    @Test(expected = IllegalArgumentException.class)
    public void UnitOfWork_StateUnderTest_ExpectedBehavior0() { // TODO should change method name
        sampleRepository.findByName(null);
    }

    @Transactional
    //@Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior1() { // TODO should change method name
        testData(5L);
        List<Sample> samples = sampleRepository.findAll();
        samples.stream().forEach(x -> {
            if (log.isDebugEnabled()) {
                log.debug(x.toString());
            }
        });
    }

    @Transactional
    //@Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior2() { // TODO should change method name
        testData(10L);
        Sample x = sampleRepository.findByName("Name1");
        if (log.isDebugEnabled()) {
            log.debug(x.toString());
        }
    }

    @Transactional
    //@Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior3() { // TODO should change method name
        sampleRepository.bulkUpdateNameByIds("Hoge", Stream.of(1L, 2L).collect(toList()));
//        sampleRepository.bulkUpdateNameByIds("Hoge", Collections.emptyList());
//javax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.2.v20140319-9ad6abd): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 72.
//Error Code: 20000
//Call: UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = 'Hoge' WHERE (ID IN ())
//Query: UpdateAllQuery(referenceClass=Sample sql="UPDATE SAMPLE SET VERSION = (VERSION + ?), NAME = ? WHERE (ID IN ())")
// :
// :
        List<Sample> samples = sampleRepository.findAll();
        samples.stream().forEach(x -> {
            if (log.isDebugEnabled()) {
                log.debug(x.toString());
            }
        });
    }

    @Transactional
    //@Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior4() { // TODO should change method name
        sampleRepository.bulkDeleteByIds(Stream.of(1L, 2L).collect(toList()));
//        sampleRepository.bulkDeleteByIds(Collections.emptyList());
//javax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.2.v20140319-9ad6abd): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 34.
//Error Code: 20000
//Call: DELETE FROM SAMPLE WHERE (ID IN ())
//Query: DeleteAllQuery(referenceClass=Sample sql="DELETE FROM SAMPLE WHERE (ID IN ())")
// :
// :
        List<Sample> samples = sampleRepository.findAll();
        samples.stream().forEach(x -> {
            if (log.isDebugEnabled()) {
                log.debug(x.toString());
            }
        });
    }

    @Transactional
    //@Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior5() { // TODO should change method name
        if (log.isDebugEnabled()) {
            log.debug(sampleRepository.find(1L).toString());
        }
        //sampleRepository.bulkDeleteByIds(Stream.of(1L, 2L).collect(toList()));
        sampleRepository.bulkUpdateNameByIds("Hoge", Stream.of(1L, 2L).collect(toList()));
        if (log.isDebugEnabled()) {
            log.debug(sampleRepository.find(1L).toString());
        }
    }

    @Transactional
    //@Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior6() { // TODO should change method name
        if (log.isDebugEnabled()) {
            log.debug(sampleRepository.find(1L).toString());
        }
    }

    private void testData(long size) {
        Stream.iterate(1L, i -> i + 1).limit(size).forEach(i -> {
            Sample sample = sampleRepository.find(i);
            if (sample == null) {
                sampleRepository.store(new Sample(i, "Name" + i));
            }
        });
    }
}
