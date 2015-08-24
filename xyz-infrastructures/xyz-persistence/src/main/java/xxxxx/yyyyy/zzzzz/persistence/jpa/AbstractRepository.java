package xxxxx.yyyyy.zzzzz.persistence.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared.Repository;

@lombok.extern.slf4j.Slf4j
@ArgumentsValidation
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
                log.debug("#store -> persist {}", entity.toString());
            }
            entityManager.persist(entity);
            return entity;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("#store -> merge {}", entity.toString());
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

    protected T singleResult(ToCriteriaQueryFunction<T> criteria) {
        return createQuery(criteria).getSingleResult();
    }

    protected List<T> resultList(ToCriteriaQueryFunction<T> criteria) {
        return createQuery(criteria).getResultList();
    }

    private TypedQuery<T> createQuery(ToCriteriaQueryFunction<T> criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        return entityManager.createQuery(criteria.apply(cb, cq, cq.from(entityClass)));
    }
//
//    protected void bulkUpdate(ToCriteriaUpdateFunction<T> criteria) {
//        int count = createQuery(criteria).executeUpdate();
//        if (log.isTraceEnabled()) {
//            log.trace("#bulkUpdate -> count:{}", count);
//        }
//        entityManager.flush();
//        entityManager.clear();
//        entityManager.getEntityManagerFactory().getCache().evictAll();
//    }
//
//    protected void bulkDelete(ToCriteriaDeleteFunction<T> criteria) {
//        int count = createQuery(criteria).executeUpdate();
//        if (log.isTraceEnabled()) {
//            log.trace("#bulkDelete -> count:{}", count);
//        }
//        entityManager.flush();
//        entityManager.clear();
//        entityManager.getEntityManagerFactory().getCache().evictAll();
//    }
//
//    private Query createQuery(ToCriteriaUpdateFunction<T> criteria) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaUpdate<T> cu = cb.createCriteriaUpdate(entityClass);
//        return entityManager.createQuery(criteria.apply(cb, cu, cu.from(entityClass)));
//    }
//
//    private Query createQuery(ToCriteriaDeleteFunction<T> criteria) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
//        return entityManager.createQuery(criteria.apply(cb, cd, cd.from(entityClass)));
//    }
}
