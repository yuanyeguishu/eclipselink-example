package xxxxx.yyyyy.zzzzz.domain.shared;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.PageableRepository;

@lombok.extern.slf4j.Slf4j
public abstract class AbstractRepository<T extends AggregateRoot<T, ID>, ID extends Serializable> implements PageableRepository<T, ID> {

    protected final Class<T> entityClass;
    protected final Class<ID> idClass;

    //@SuppressWarnings("unchecked")
    public AbstractRepository() {
        ParameterizedType parameterizedType = parameterizedType();
        this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        this.idClass = (Class<ID>) parameterizedType.getActualTypeArguments()[1];
    }

    private ParameterizedType parameterizedType() {
        return (isProxyClass())
                ? (ParameterizedType) getClass().getSuperclass().getGenericSuperclass()
                : (ParameterizedType) getClass().getGenericSuperclass();
    }

    protected abstract boolean isProxyClass();
//
//    protected Class<T> entityClass() {
//        return this.entityClass;
//    }
//
//    protected Class<ID> idClass() {
//        return this.idClass;
//    }
}
