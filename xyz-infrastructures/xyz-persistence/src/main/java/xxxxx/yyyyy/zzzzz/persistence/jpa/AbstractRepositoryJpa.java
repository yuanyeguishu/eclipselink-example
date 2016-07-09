package xxxxx.yyyyy.zzzzz.persistence.jpa;

import static xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import xxxxx.yyyyy.zzzzz.domain.shared.AbstractRepository;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Page;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Pagination;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Sort;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;
import xxxxx.yyyyy.zzzzz.persistence.jpa._experimental.Trace;
import xxxxx.yyyyy.zzzzz.persistence.jpa._experimental.Valid;

@lombok.extern.slf4j.Slf4j
@Trace
@Valid
public abstract class AbstractRepositoryJpa<T extends AggregateRoot<T, ID>, ID extends Serializable> extends AbstractRepository<T, ID> {

    protected final EntityManager entityManager;
    protected final EntityType<T> entityType;

    //@SuppressWarnings("unchecked")
    public AbstractRepositoryJpa(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
        this.entityType = entityManager.getMetamodel().entity(this.entityClass);
    }
//
//    @Inject
//    protected EntityManager entityManager;

    @Override
    protected boolean isProxyClass() {
        // $Proxy$_$$_WeldSubclass
        // $Proxy$_$$_WeldClientProxy
        return getClass().getSimpleName().matches(".*\\$Proxy\\$_\\$\\$_Weld.*");
    }
//
//    protected Class<T> entityClass() {
//        return this.entityClass;
//    }
//
//    protected Class<ID> idClass() {
//        return this.idClass;
//    }
//
//    protected EntityManager entityManager() {
//        return this.entityManager;
//    }

    @Override
    public <U extends T> U store(U entity) {
        if (entity.isNew()) {
            this.entityManager.persist(entity);
            return entity;
        } else {
            return this.entityManager.merge(entity);
        }
    }

    @Override
    public <U extends T> List<U> store(List<U> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <U extends T> void remove(U entity) {
        this.entityManager.remove(
                this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
    }

    @Override
    public <U extends T> void remove(List<U> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T find(ID id) {
        return this.entityManager.find(this.entityClass, id);
    }

    @Override
    public List<T> findAll(Specification<T> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<T> findAll(Sort<T> sort) {
        CriteriaBuilder b = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = b.createQuery(this.entityClass);
        Root<T> r = q.from(this.entityClass);
        q.orderBy(sort.orders().stream()
                // TODO
                //.peek(x -> {
                //    boolean exists = this.entityType.getAttributes().stream().anyMatch(y -> y.getName().equals(x.property()));
                //    if (!exists) {
                //    }
                //})
                .collect(Collector.of(() -> new ArrayList<>(),
                        (x, y) -> x.add((y.direction() == ASCENDING)
                                ? b.asc(r.get(y.property())) : b.desc(r.get(y.property()))),
                        (x, y) -> {
                            throw new UnsupportedOperationException();
                        }
                )));
        return this.entityManager.createQuery(q).getResultList();
    }

    @Override
    public List<T> findAll(Specification<T> specification, Sort<T> sort) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<T> findAll(Pagination<T> pagination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page<T> findAll(Specification<T> specification, Pagination<T> pagination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
