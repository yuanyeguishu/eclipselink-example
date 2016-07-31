package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import java.util.Optional;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);

    boolean isGeneralizationOf(Specification<T> specification);

    default Specification<T> and(Specification<T> specification) {
        return new ConjunctionSpecification<T>().with(this).with(specification);
    }

    default Specification<T> or(Specification<T> specification) {
        return new DisjunctionSpecification<T>().with(this).with(specification);
    }

    default Optional<Specification<T>> remainderUnsatisfiedBy(T candidate) {
        return (isSatisfiedBy(candidate)) ? Optional.empty() : Optional.of(this);
    }

    default boolean isSpecialCaseOf(Specification<T> specification) {
        if (specification == null) {
            return false;
        }
        if (specification.equals(this)) {
            return true;
        }
        return specification.isGeneralizationOf(this);
    }
}
