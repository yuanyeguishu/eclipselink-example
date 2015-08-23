package xxxxx.yyyyy.zzzzz.persistence.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared.Repository;

@lombok.extern.slf4j.Slf4j
public abstract class AbstractRepository<T extends AggregateRoot<T, ID>, ID extends Serializable> implements Repository<T, ID> {

    protected final Class<T> entityClass;
    protected final Class<ID> idClass;
    @Inject
    protected EntityManager entityManager;

    //@SuppressWarnings("unchecked")
    public AbstractRepository() {
        ParameterizedType parameterizedType
                = (getClass().getSimpleName().contains("$Proxy$"))
                        ? (ParameterizedType) getClass().getSuperclass().getGenericSuperclass()
                        : (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
    }
//
//    private ParameterizedType getParameterizedType() {
//        return (isProxyClass())
//                ? (ParameterizedType) getClass().getSuperclass().getGenericSuperclass()
//                : (ParameterizedType) getClass().getGenericSuperclass();
//    }
//
//    private boolean isProxyClass() {
//        // $Proxy$_$$_WeldSubclass
//        // $Proxy$_$$_WeldClientProxy
//        return getClass().getSimpleName().contains("$Proxy$");
//    }

    @Override
    public <U extends T> U store(U entity) {
        if (entity.isNew()) {
            if (log.isDebugEnabled()) {
                log.debug("persist {}", entity.toString());
            }
            entityManager.persist(entity);
            return entity;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("merge {}", entity.toString());
            }
            return entityManager.merge(entity);
        }
    }

    @Override
    public <U extends T> void remove(U entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public T find(final ID id) {
        return entityManager.find(entityClass, id);
    }

    protected TypedQuery<T> createQuery(Queryable<T> queryable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> r = cq.from(entityClass);
        return entityManager.createQuery(queryable.query(cb, cq, r));
    }

    protected TypedQuery<T> createQuery(Updatable<T> updatable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<T> cu = cb.createCriteriaUpdate(entityClass);
        Root<T> r = cu.from(entityClass);
        return entityManager.createQuery(updatable.update(cb, cu, r));
    }

    protected TypedQuery<T> createQuery(Deletable<T> deletable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
        Root<T> r = cd.from(entityClass);
        return entityManager.createQuery(deletable.delete(cb, cd, r));
    }

    protected T singleResult(Queryable<T> queryable) {
        return createQuery(queryable).getSingleResult();
    }

    protected T singleResultOrNull(Queryable<T> queryable) {
        return oneOrNull(resultList(queryable));
    }

    protected List<T> resultList(Queryable<T> queryable) {
        return createQuery(queryable).getResultList();
    }
//
//    protected List<T> resultList(Queryable<T> queryable, int startPosition, int maxResult) {
//        return createQuery(queryable).setFirstResult(startPosition).setMaxResults(maxResult).resultList();
//    }

    protected T oneOrNull(List<T> resultList) {
        if (resultList == null || resultList.isEmpty()) {
            return null;
        } else {
            if (resultList.size() == 1) {
                return resultList.get(0);
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
