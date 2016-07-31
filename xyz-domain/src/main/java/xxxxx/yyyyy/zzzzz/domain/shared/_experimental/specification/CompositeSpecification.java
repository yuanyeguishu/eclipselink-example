package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import java.util.List;

public interface CompositeSpecification<T> extends Specification<T> {

    CompositeSpecification<T> with(Specification<T> specification);

    default CompositeSpecification<T> with(List<Specification<T>> specifications) {
        if (specifications == null) {
            throw new IllegalArgumentException("'specifications' must not be null");
        }
        specifications.stream().forEach(this::with);
        return this;
    }

    List<Specification<T>> unmodifiableComponents();
}
