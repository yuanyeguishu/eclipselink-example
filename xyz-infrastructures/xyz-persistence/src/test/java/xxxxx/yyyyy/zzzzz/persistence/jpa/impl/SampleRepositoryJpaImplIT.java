package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

import static java.util.stream.Collectors.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.model.SampleRepository;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Sort;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.impl.OrderImpl;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.impl.SortImpl;
import xxxxx.yyyyy.zzzzz.persistence.POM;

@lombok.extern.slf4j.Slf4j
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class SampleRepositoryJpaImplIT {

    @Inject
    private EntityManager entityManager;
    @Inject
    private SampleRepository sampleRepository;
    @Rule
    public TestName name = new TestName();

    @Before
    public void before() throws Exception {
        log.info(String.format("[START] %s", name.getMethodName()));
        try {
            this.entityManager.createQuery("delete from Sample").executeUpdate();
            this.entityManager.persist(new Sample(1L, "Name1"));
            this.entityManager.persist(new Sample(2L, "Name2"));
            this.entityManager.persist(new Sample(3L, "Name3"));
            this.entityManager.persist(new Sample(4L, "Name4"));
            this.entityManager.persist(new Sample(5L, "Name5"));
            this.entityManager.flush();
            this.entityManager.clear();
            this.entityManager.getEntityManagerFactory().getCache().evictAll();
        } finally {
        }
    }

    @After
    public void after() {
        log.info("[END]\n\n\n\n\n");
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
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior1() { // TODO should change method name
        List<Sample> samples = sampleRepository.findAll();
        assertThat(samples.size(), is(5));
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior2() { // TODO should change method name
        Sample sample = sampleRepository.findByName("Name1");
        assertThat(sample.id(), is(1L));
        assertThat(sample.name(), is("Name1"));
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior3() { // TODO should change method name
        sampleRepository.bulkUpdateNameByIds("Hoge", Stream.of(1L, 2L).collect(toList()));
        List<Sample> samples = sampleRepository.findAll();
        assertThat(samples.size(), is(5));
        Sample sample1 = samples.stream().filter(x -> x.id() == 1L).findFirst().get();
        Sample sample2 = samples.stream().filter(x -> x.id() == 2L).findFirst().get();
        Sample sample3 = samples.stream().filter(x -> x.id() == 3L).findFirst().get();
        Sample sample4 = samples.stream().filter(x -> x.id() == 4L).findFirst().get();
        Sample sample5 = samples.stream().filter(x -> x.id() == 5L).findFirst().get();
        assertThat(sample1.name(), is("Hoge"));
        assertThat(sample2.name(), is("Hoge"));
        assertThat(sample3.name(), is("Name3"));
        assertThat(sample4.name(), is("Name4"));
        assertThat(sample5.name(), is("Name5"));
    }

    @Transactional
    @Test(expected = javax.persistence.PersistenceException.class)
    public void UnitOfWork_StateUnderTest_ExpectedBehavior3_2() { // TODO should change method name
        sampleRepository.bulkUpdateNameByIds("Hoge", Collections.emptyList());
//UnitOfWork_StateUnderTest_ExpectedBehavior3_2(xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT)  Time elapsed: 3.154 sec  <<< ERROR!
//javax.persistence.PersistenceException:
//Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.6.2.qualifier): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 72.
//Error Code: 20000
//Call: UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = 'Hoge' WHERE (ID IN ())
//Query: UpdateAllQuery(referenceClass=Sample sql="UPDATE SAMPLE SET VERSION = (VERSION + ?), NAME = ? WHERE (ID IN ())")
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior3_2(SampleRepositoryJpaImplIT.java:139)
//Caused by: org.eclipse.persistence.exceptions.DatabaseException:
//
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 72.
//Error Code: 20000
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior3_2(SampleRepositoryJpaImplIT.java:139)
//Caused by: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 72.
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior3_2(SampleRepositoryJpaImplIT.java:139)
//Caused by: org.apache.derby.impl.jdbc.EmbedSQLException: Syntax error: Encountered ")" at line 1, column 72.
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior3_2(SampleRepositoryJpaImplIT.java:139)
//Caused by: org.apache.derby.iapi.error.StandardException: Syntax error: Encountered ")" at line 1, column 72.
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior3_2(SampleRepositoryJpaImplIT.java:139)
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior4() { // TODO should change method name
        sampleRepository.bulkDeleteByIds(Stream.of(1L, 2L).collect(toList()));
        List<Sample> samples = sampleRepository.findAll();
        assertThat(samples.size(), is(3));
    }

    @Transactional
    @Test(expected = javax.persistence.PersistenceException.class)
    public void UnitOfWork_StateUnderTest_ExpectedBehavior4_2() { // TODO should change method name
        sampleRepository.bulkDeleteByIds(Collections.emptyList());
//UnitOfWork_StateUnderTest_ExpectedBehavior4_2(xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT)  Time elapsed: 0.301 sec  <<< ERROR!
//javax.persistence.PersistenceException:
//Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.6.2.qualifier): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 34.
//Error Code: 20000
//Call: DELETE FROM SAMPLE WHERE (ID IN ())
//Query: DeleteAllQuery(referenceClass=Sample sql="DELETE FROM SAMPLE WHERE (ID IN ())")
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior4_2(SampleRepositoryJpaImplIT.java:165)
//Caused by: org.eclipse.persistence.exceptions.DatabaseException:
//
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 34.
//Error Code: 20000
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior4_2(SampleRepositoryJpaImplIT.java:165)
//Caused by: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 34.
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior4_2(SampleRepositoryJpaImplIT.java:165)
//Caused by: org.apache.derby.impl.jdbc.EmbedSQLException: Syntax error: Encountered ")" at line 1, column 34.
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior4_2(SampleRepositoryJpaImplIT.java:165)
//Caused by: org.apache.derby.iapi.error.StandardException: Syntax error: Encountered ")" at line 1, column 34.
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryJpaImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior4_2(SampleRepositoryJpaImplIT.java:165)
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior5() { // TODO should change method name
//        Sort<Sample> sort = new SortImpl<>(
//                new JpaOrderImpl<>(Sample_.name, Direction.DESCENDING),
//                new JpaOrderImpl<>(Sample_.id, Direction.ASCENDING));
        Sort<Sample> sort = new SortImpl<>(
                new OrderImpl<Sample>("name", Direction.DESCENDING),
                new OrderImpl<Sample>("id", Direction.ASCENDING));
        List<Sample> samples = sampleRepository.findAll(sort);
        samples.stream().forEach(System.out::println);
    }
}
