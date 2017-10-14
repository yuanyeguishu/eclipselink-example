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
//package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa;
//
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
////@lombok.extern.slf4j.Slf4j
//@RequestScoped
//public class ContainerManagedEntityManagerProducer {
//
//    @PersistenceContext //(unitName = PERSISTENCE_UNIT_NAME)
//    private EntityManager entityManager;
//
//    @RequestScoped
//    @Produces
//    public EntityManager produce() {
//        if (log.isTraceEnabled()) {
//            log.trace(String.format("@Produces -> %s", this.entityManager.toString()));
//        }
//        return this.entityManager;
//    }
//
//    public void dispose(@Disposes EntityManager entityManager) {
//        if (log.isTraceEnabled()) {
//            log.trace(String.format("@Disposes -> %s", entityManager.toString()));
//        }
//// Only an application-managed entity manager...
//// @see javax.persistence.EntityManager.close()
////        if (entityManager != null && entityManager.isOpen()) {
////            entityManager.close();
////        }
//    }
//}
