package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared.Repository;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;

public interface SortableRepository<T extends AggregateRoot<T, ID>, ID extends Serializable> extends Repository<T, ID> {

    List<T> findAll(Sort<T> sort);

    List<T> findAll(Specification<T> specification, Sort<T> sort);
}
