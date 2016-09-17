package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.UUID;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;

@lombok.extern.slf4j.Slf4j
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class BeanManagedTransactionsIT {

    @Inject
    private UserTransaction userTransaction;
    @Inject
    private EntityManager entityManager;

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
                .addPackages(true, "xxxxx.yyyyy.zzzzz.xyz")
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
        this.begin();
        this.entityManager.createQuery("delete from Sample").executeUpdate();
        this.commit();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior0() { // TODO should change method name
        this.begin();
        this.entityManager.persist(new Sample(1L, UUID.randomUUID().toString()));
        this.commit();
        assertThat("", this.entityManager.createQuery("select count(x) from Sample as x", Long.class).getSingleResult(), equalTo(1L));
        this.begin();
        this.entityManager.persist(new Sample(2L, UUID.randomUUID().toString()));
        this.rollback();
        assertThat("", this.entityManager.createQuery("select count(x) from Sample as x", Long.class).getSingleResult(), equalTo(1L));
        this.begin();
        this.entityManager.persist(new Sample(2L, UUID.randomUUID().toString()));
        this.commit();
        assertThat("", this.entityManager.createQuery("select count(x) from Sample as x", Long.class).getSingleResult(), equalTo(2L));
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
