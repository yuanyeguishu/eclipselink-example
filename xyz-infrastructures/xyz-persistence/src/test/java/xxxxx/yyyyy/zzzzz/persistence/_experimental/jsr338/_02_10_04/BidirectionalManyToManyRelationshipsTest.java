package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_04;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

public class BidirectionalManyToManyRelationshipsTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("_02_10_04");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
//
// CREATE TABLE EMPLOYEE (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// CREATE TABLE PROJECT (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// CREATE TABLE PROJECT_EMPLOYEE (employees_ID BIGINT NOT NULL, projects_ID BIGINT NOT NULL, PRIMARY KEY (employees_ID, projects_ID))
// ALTER TABLE PROJECT_EMPLOYEE ADD CONSTRAINT FK_PROJECT_EMPLOYEE_projects_ID FOREIGN KEY (projects_ID) REFERENCES PROJECT (ID)
// ALTER TABLE PROJECT_EMPLOYEE ADD CONSTRAINT FK_PROJECT_EMPLOYEE_employees_ID FOREIGN KEY (employees_ID) REFERENCES EMPLOYEE (ID)
//
    }
}
