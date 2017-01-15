/**
 * Copyright © 2015 Masamitsu Namioka (masamitsunamioka@gmail.com)
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
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.jsr338._02_10_05_01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;

public class UnidirectionalOneToManyRelationshipsTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("_02_10_05_01");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
//
// CREATE TABLE EMPLOYEE (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// CREATE TABLE ANNUALREVIEW (ID BIGINT NOT NULL, PRIMARY KEY (ID))
// CREATE TABLE EMPLOYEE_ANNUALREVIEW (Employee_ID BIGINT NOT NULL, annualReviews_ID BIGINT NOT NULL, PRIMARY KEY (Employee_ID, annualReviews_ID))
// ALTER TABLE EMPLOYEE_ANNUALREVIEW ADD CONSTRAINT FK_EMPLOYEE_ANNUALREVIEW_annualReviews_ID FOREIGN KEY (annualReviews_ID) REFERENCES ANNUALREVIEW (ID)
// ALTER TABLE EMPLOYEE_ANNUALREVIEW ADD CONSTRAINT FK_EMPLOYEE_ANNUALREVIEW_Employee_ID FOREIGN KEY (Employee_ID) REFERENCES EMPLOYEE (ID)
//
    }
}
