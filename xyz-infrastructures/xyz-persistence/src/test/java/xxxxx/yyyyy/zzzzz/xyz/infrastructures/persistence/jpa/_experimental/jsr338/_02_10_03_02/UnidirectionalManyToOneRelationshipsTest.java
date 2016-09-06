package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338._02_10_03_02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

public class UnidirectionalManyToOneRelationshipsTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("_02_10_03_02");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
//
// CREATE TABLE EMPLOYEE (ID BIGINT NOT NULL, ADDRESS_ID BIGINT, PRIMARY KEY (ID))
// CREATE TABLE ADDRESS (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_ADDRESS_ID FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ID)
//
    }
}
