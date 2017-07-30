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
package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.impl.sample;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import xxxxx.yyyyy.zzzzz.xyz.application.shared._experimental.TraceBeanLifecycle;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.Sample;
import xxxxx.yyyyy.zzzzz.xyz.domain.model.sample.SampleRepository;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa.AbstractRepository;

@lombok.extern.slf4j.Slf4j
@TraceBeanLifecycle
//@Typed(value = SampleRepository.class)
@ApplicationScoped
public class SampleRepositoryImpl extends AbstractRepository<Sample, Long> implements SampleRepository {

    @Inject
    public SampleRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @PostConstruct
    void postConstruct() {
    }

    @PreDestroy
    void preDestroy() {
    }

    @Override
    public List<Sample> findAll() {
        if (log.isDebugEnabled()) {
            log.trace(String.format("%s -> %s", "findAll", this.entityManager.toString()));
        }
//        return resultList(entityManager, entityClass, (b, q, r) -> {
//            return q.select(r);
//        });
        final String jpql = "select x from Sample as x";
        return this.entityManager
                .createQuery(jpql, Sample.class)
                .getResultList();
    }

    //@Valid(ignore = true)
    @Override
    public Sample findByName(String name) {
//        return singleResult(entityManager, entityClass, (b, q, r) -> {
//            return q.select(r).where(b.equal(r.get(Sample_.name), name));
//        });
        final String jpql = "select x from Sample as x where x.name = :name";
        return this.entityManager
                .createQuery(jpql, Sample.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString())
                .append(" {")
                .append("this.entityManager=").append((this.entityManager == null) ? "null" : this.entityManager.toString())
                //.append(", ")
                .append("}")
                .toString();
    }
}
