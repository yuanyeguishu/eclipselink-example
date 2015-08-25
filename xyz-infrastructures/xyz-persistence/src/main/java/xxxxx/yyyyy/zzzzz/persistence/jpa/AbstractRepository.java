package xxxxx.yyyyy.zzzzz.persistence.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared.Repository;
import xxxxx.yyyyy.zzzzz.persistence.jpa._experimental.RepositoryValidation;

@lombok.extern.slf4j.Slf4j
@RepositoryValidation
public abstract class AbstractRepository<T extends AggregateRoot<T, ID>, ID extends Serializable> implements Repository<T, ID> {

    protected final EntityManager entityManager;
    protected final Class<T> entityClass;
    protected final Class<ID> idClass;

    //@SuppressWarnings("unchecked")
    public AbstractRepository(final EntityManager entityManager) {
        ParameterizedType parameterizedType = parameterizedType();
        this.entityManager = entityManager;
        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
    }
//
//    @Inject
//    protected EntityManager entityManager;
//
//    //@SuppressWarnings("unchecked")
//    public AbstractRepository() {
//        ParameterizedType parameterizedType = parameterizedType();
//        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
//        this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
//    }

    private ParameterizedType parameterizedType() {
        return (isProxyClass())
                ? (ParameterizedType) getClass().getSuperclass().getGenericSuperclass()
                : (ParameterizedType) getClass().getGenericSuperclass();
    }

    private boolean isProxyClass() {
        // $Proxy$_$$_WeldSubclass
        // $Proxy$_$$_WeldClientProxy
        return getClass().getSimpleName().contains("$Proxy$");
    }

    @Override
    public <U extends T> U store(final U entity) {
        if (entity.isNew()) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Override
    public <U extends T> void remove(final U entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public T find(final ID id) {
        return entityManager.find(entityClass, id);
    }
}
