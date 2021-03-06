/**
 * Copyright © 2015-2017 Masamitsu Namioka (masamitsunamioka@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338._02_10_04;

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
