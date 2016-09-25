package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import xxxxx.yyyyy.zzzzz.xyz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.xyz.domain.shared.Repository;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.Trace;
import xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental.Valid;

@lombok.extern.slf4j.Slf4j
@Trace
@Valid
public abstract class AbstractRepository<T extends AggregateRoot<T, ID>, ID extends Serializable> implements Repository<T, ID> {

    protected final EntityManager entityManager;
    protected final Class<T> entityClass;
    protected final Class<ID> idClass;

    //@SuppressWarnings("unchecked")
    public AbstractRepository(EntityManager entityManager) {
        ParameterizedType parameterizedType = parameterizedType();
        this.entityManager = entityManager;
        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
    }
//
//    @PersistenceContext //@Inject
//    protected EntityManager entityManager;
//    protected final Class<T> entityClass;
//    protected final Class<ID> idClass;
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
        return getClass().getSimpleName().matches(".*\\$Proxy\\$_\\$\\$_Weld.*");
    }

    @Override
    public <U extends T> U store(U entity) {
        if (log.isDebugEnabled()) {
            log.trace(String.format("%s -> %s", "store", this.entityManager.toString()));
        }
        // JSR 338 7.7 Application-managed Persistence Contexts
        if (!this.entityManager.isJoinedToTransaction()) {
            this.entityManager.joinTransaction();
        }
        if (entity.isNew()) {
            this.entityManager.persist(entity);
            return entity;
        } else {
            return this.entityManager.merge(entity);
        }
    }

    @Override
    public <U extends T> List<U> store(List<U> entities) {
        // TODO http://www.eclipse.org/eclipselink/documentation/2.5/jpa/extensions/p_jdbc_batchwriting.htm
        // TODO http://www.eclipse.org/eclipselink/documentation/2.5/jpa/extensions/p_jdbc_batchwritingsize.htm#CIHJADHF
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <U extends T> void remove(U entity) {
        if (log.isDebugEnabled()) {
            log.trace(String.format("%s -> %s", "remove", this.entityManager.toString()));
        }
        // JSR 338 7.7 Application-managed Persistence Contexts
        if (!this.entityManager.isJoinedToTransaction()) {
            this.entityManager.joinTransaction();
        }
        this.entityManager.remove(
                this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
    }

    @Override
    public <U extends T> void remove(List<U> entities) {
        // TODO http://www.eclipse.org/eclipselink/documentation/2.5/jpa/extensions/p_jdbc_batchwriting.htm
        // TODO http://www.eclipse.org/eclipselink/documentation/2.5/jpa/extensions/p_jdbc_batchwritingsize.htm#CIHJADHF
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T find(ID id) {
        if (log.isDebugEnabled()) {
            log.trace(String.format("%s -> %s", "find", this.entityManager.toString()));
        }
        return this.entityManager.find(this.entityClass, id);
    }
}
