package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;

public interface PageableRepository<T extends AggregateRoot<T, ID>, ID extends Serializable> extends SortableRepository<T, ID> {

    Page<T> findAll(Pagination<T> pagination);

    Page<T> findAll(Specification<T> specification, Pagination<T> pagination);
}
