package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

public class F<T extends AggregateRoot<T, ID>, ID extends Serializable> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public F(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public TypedQuery<T> q(ToCriteriaQueryFunction<T> f) {
        CriteriaBuilder x = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> y = x.createQuery(entityClass);
        Root<T> z = y.from(entityClass);
        return entityManager.createQuery(f.apply(x, y, z));
    }
}
