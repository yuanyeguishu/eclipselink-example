package xxxxx.yyyyy.zzzzz.persistence.jpa.impl;

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
        //.addAsResource("logging.properties", "logging.properties");
        war.addAsLibraries(jar);
        if (log.isDebugEnabled()) {
            log.debug(war.toString(true));
        }
        return war;
    }

    @Transactional
    @Test
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
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior2() { // TODO should change method name
        testData(10L);
        Sample x = sampleRepository.findByName("Name1");
        if (log.isDebugEnabled()) {
            log.debug(x.toString());
        }
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior3() { // TODO should change method name
        sampleRepository.updateNameByIds("Hoge", Stream.of(1L, 2L).collect(toList()));
//        sampleRepository.updateNameByIds("Hoge", Collections.emptyList());
//javax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.2.v20140319-9ad6abd): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 72.
//Error Code: 20000
//Call: UPDATE SAMPLE SET VERSION = (VERSION + 1), NAME = 'Hoge' WHERE (ID IN ())
//Query: UpdateAllQuery(referenceClass=Sample sql="UPDATE SAMPLE SET VERSION = (VERSION + ?), NAME = ? WHERE (ID IN ())")
//        at org.apache.derby.iapi.error.StandardException.newException(Unknown Source)
//        at org.apache.derby.impl.sql.compile.ParserImpl.parseStatement(Unknown Source)
//        at org.apache.derby.impl.sql.GenericStatement.prepMinion(Unknown Source)
//        at org.apache.derby.impl.sql.GenericStatement.prepare(Unknown Source)
//        at org.apache.derby.impl.sql.conn.GenericLanguageConnectionContext.prepareInternalStatement(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedStatement.execute(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedStatement.executeLargeUpdate(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedStatement.executeUpdate(Unknown Source)
//        at com.sun.gjc.spi.base.StatementWrapper.executeUpdate(StatementWrapper.java:118)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeDirectNoSelect(DatabaseAccessor.java:888)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeNoSelect(DatabaseAccessor.java:962)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.basicExecuteCall(DatabaseAccessor.java:631)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeCall(DatabaseAccessor.java:558)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.basicExecuteCall(AbstractSession.java:2002)
//        at org.eclipse.persistence.sessions.server.ClientSession.executeCall(ClientSession.java:298)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:242)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:228)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeNoSelectCall(DatasourceCallQueryMechanism.java:271)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.updateAll(DatasourceCallQueryMechanism.java:835)
//        at org.eclipse.persistence.queries.UpdateAllQuery.executeDatabaseQuery(UpdateAllQuery.java:154)
//        at org.eclipse.persistence.queries.DatabaseQuery.execute(DatabaseQuery.java:899)
//        at org.eclipse.persistence.queries.DatabaseQuery.executeInUnitOfWork(DatabaseQuery.java:798)
//        at org.eclipse.persistence.queries.ModifyAllQuery.executeInUnitOfWork(ModifyAllQuery.java:148)
//        at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.internalExecuteQuery(UnitOfWorkImpl.java:2896)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1804)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1786)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1751)
//        at org.eclipse.persistence.internal.jpa.QueryImpl.executeUpdate(QueryImpl.java:298)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.AbstractRepository.executeUpdate(AbstractRepository.java:112)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryImpl.updateNameByIds(SampleRepositoryImpl.java:36)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryImpl$Proxy$_$$_WeldClientProxy.updateNameByIds(Unknown Source)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior3(SampleRepositoryImplIT.java:82)
        List<Sample> samples = sampleRepository.findAll();
        samples.stream().forEach(x -> {
            if (log.isDebugEnabled()) {
                log.debug(x.toString());
            }
        });
    }

    @Transactional
    @Test
    public void UnitOfWork_StateUnderTest_ExpectedBehavior4() { // TODO should change method name
        sampleRepository.removeByIds(Stream.of(1L, 2L).collect(toList()));
//        sampleRepository.removeByIds(Collections.emptyList());
//javax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.2.v20140319-9ad6abd): org.eclipse.persistence.exceptions.DatabaseException
//Internal Exception: java.sql.SQLSyntaxErrorException: Syntax error: Encountered ")" at line 1, column 34.
//Error Code: 20000
//Call: DELETE FROM SAMPLE WHERE (ID IN ())
//Query: DeleteAllQuery(referenceClass=Sample sql="DELETE FROM SAMPLE WHERE (ID IN ())")
//        at org.apache.derby.iapi.error.StandardException.newException(Unknown Source)
//        at org.apache.derby.impl.sql.compile.ParserImpl.parseStatement(Unknown Source)
//        at org.apache.derby.impl.sql.GenericStatement.prepMinion(Unknown Source)
//        at org.apache.derby.impl.sql.GenericStatement.prepare(Unknown Source)
//        at org.apache.derby.impl.sql.conn.GenericLanguageConnectionContext.prepareInternalStatement(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedPreparedStatement.<init>(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedPreparedStatement20.<init>(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedPreparedStatement30.<init>(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedPreparedStatement40.<init>(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedPreparedStatement42.<init>(Unknown Source)
//        at org.apache.derby.jdbc.Driver42.newEmbedPreparedStatement(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedConnection.prepareStatement(Unknown Source)
//        at org.apache.derby.impl.jdbc.EmbedConnection.prepareStatement(Unknown Source)
//        at com.sun.gjc.spi.base.ConnectionHolder.prepareStatement(ConnectionHolder.java:586)
//        at com.sun.gjc.spi.jdbc40.ConnectionWrapper40.prepareCachedStatement(ConnectionWrapper40.java:255)
//        at com.sun.gjc.spi.jdbc40.ConnectionWrapper40.prepareCachedStatement(ConnectionWrapper40.java:52)
//        at com.sun.gjc.spi.ManagedConnectionImpl.prepareCachedStatement(ManagedConnectionImpl.java:992)
//        at com.sun.gjc.spi.jdbc40.ConnectionWrapper40.prepareStatement(ConnectionWrapper40.java:173)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.prepareStatement(DatabaseAccessor.java:1556)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.prepareStatement(DatabaseAccessor.java:1505)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseCall.prepareStatement(DatabaseCall.java:778)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.basicExecuteCall(DatabaseAccessor.java:619)
//        at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeCall(DatabaseAccessor.java:558)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.basicExecuteCall(AbstractSession.java:2002)
//        at org.eclipse.persistence.sessions.server.ClientSession.executeCall(ClientSession.java:298)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:242)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:228)
//        at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.deleteAll(DatasourceCallQueryMechanism.java:127)
//        at org.eclipse.persistence.queries.DeleteAllQuery.executeDatabaseQuery(DeleteAllQuery.java:201)
//        at org.eclipse.persistence.queries.DatabaseQuery.execute(DatabaseQuery.java:899)
//        at org.eclipse.persistence.queries.DatabaseQuery.executeInUnitOfWork(DatabaseQuery.java:798)
//        at org.eclipse.persistence.queries.ModifyAllQuery.executeInUnitOfWork(ModifyAllQuery.java:148)
//        at org.eclipse.persistence.queries.DeleteAllQuery.executeInUnitOfWork(DeleteAllQuery.java:124)
//        at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.internalExecuteQuery(UnitOfWorkImpl.java:2896)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1804)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1786)
//        at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1751)
//        at org.eclipse.persistence.internal.jpa.QueryImpl.executeUpdate(QueryImpl.java:298)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.AbstractRepository.executeDelete(AbstractRepository.java:117)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryImpl.removeByIds(SampleRepositoryImpl.java:42)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryImpl$Proxy$_$$_WeldClientProxy.removeByIds(Unknown Source)
//        at xxxxx.yyyyy.zzzzz.persistence.jpa.impl.SampleRepositoryImplIT.UnitOfWork_StateUnderTest_ExpectedBehavior4(SampleRepositoryImplIT.java:95)
        List<Sample> samples = sampleRepository.findAll();
        samples.stream().forEach(x -> {
            if (log.isDebugEnabled()) {
                log.debug(x.toString());
            }
        });
    }
//    private void testData() {
//        for (long id = 1L; id <= 10L; id++) {
//            //Sample sample = new Sample();
//            Sample sample = sampleRepository.find(id);
//            if (sample == null) {
//                sample = new Sample();
//                sample.setId(id);
//                sample.setName("Name" + id);
//                sampleRepository.store(sample);
//            }
//        }
//    }

    private void testData(long size) {
        Stream.iterate(1L, i -> i + 1).limit(size).forEach(i -> {
            Sample sample = sampleRepository.find(i);
            if (sample == null) {
                sampleRepository.store(new Sample(i, "Name" + i));
            }
        });
    }
}
