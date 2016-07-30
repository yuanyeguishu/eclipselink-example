package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_05_02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

public class UnidirectionalManyToManyRelationshipsTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("_02_10_05_02");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
//
// CREATE TABLE EMPLOYEE (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// CREATE TABLE PATENT (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// CREATE TABLE EMPLOYEE_PATENT (Employee_ID BIGINT NOT NULL, patents_ID BIGINT NOT NULL, PRIMARY KEY (Employee_ID, patents_ID))
// ALTER TABLE EMPLOYEE_PATENT ADD CONSTRAINT FK_EMPLOYEE_PATENT_patents_ID FOREIGN KEY (patents_ID) REFERENCES PATENT (ID)
// ALTER TABLE EMPLOYEE_PATENT ADD CONSTRAINT FK_EMPLOYEE_PATENT_Employee_ID FOREIGN KEY (Employee_ID) REFERENCES EMPLOYEE (ID)
//
    }
}
