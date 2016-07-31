package xxxxx.yyyyy.zzzzz.domain.shared;

import java.io.Serializable;
import java.util.List;

public interface Repository<T extends AggregateRoot<T, ID>, ID extends Serializable> {

    <U extends T> U store(U entity);

    <U extends T> List<U> store(List<U> entities);

    <U extends T> void remove(U entity);

    <U extends T> void remove(List<U> entities);

    T find(ID id);

    @Deprecated
    default List<T> findAll() {
        throw new UnsupportedOperationException("Deprecated");
    }
//
//    List<T> findAll(Specification<T> specification);
}
