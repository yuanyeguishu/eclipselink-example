package xxxxx.yyyyy.zzzzz.persistence._experimental.jsr338._02_10_03_01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

public class UnidirectionalOneToOneRelationshipsTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("_02_10_03_01");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
//
// CREATE TABLE EMPLOYEE (ID BIGINT NOT NULL, TRAVELPROFILE_ID BIGINT, PRIMARY KEY (ID))
// CREATE TABLE TRAVELPROFILE (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_TRAVELPROFILE_ID FOREIGN KEY (TRAVELPROFILE_ID) REFERENCES TRAVELPROFILE (ID)
//
    }
}
