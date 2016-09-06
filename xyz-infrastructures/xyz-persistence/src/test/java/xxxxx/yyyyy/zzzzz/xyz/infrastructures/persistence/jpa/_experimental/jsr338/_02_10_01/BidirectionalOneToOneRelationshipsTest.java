package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338._02_10_01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

public class BidirectionalOneToOneRelationshipsTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("_02_10_01");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
//
// CREATE TABLE EMPLOYEE (ID BIGINT NOT NULL, ASSIGNEDCUBICLE_ID BIGINT, PRIMARY KEY (ID))
// CREATE TABLE CUBICLE (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_ASSIGNEDCUBICLE_ID FOREIGN KEY (ASSIGNEDCUBICLE_ID) REFERENCES CUBICLE (ID)
//
    }
}
