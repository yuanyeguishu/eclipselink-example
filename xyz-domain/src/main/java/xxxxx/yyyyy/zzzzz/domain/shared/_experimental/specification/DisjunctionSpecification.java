package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import static java.util.stream.Collectors.*;

import java.util.Optional;

public final class DisjunctionSpecification<T> extends AbstractCompositeSpecification<T> {

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return this.components.stream().anyMatch(x -> x.isSatisfiedBy(candidate));
    }

    @Override
    public boolean isGeneralizationOf(Specification<T> specification) {
        if (specification == null) {
            return false;
        }
        if (specification.equals(this)) {
            return true;
        }
        return this.components.stream().anyMatch(x -> x.isGeneralizationOf(specification));
    }

    @Override
    public Specification<T> and(Specification<T> specification) {
        if (this.components.isEmpty()) {
            if (specification == null) {
                throw new IllegalArgumentException("'specification' must not be null");
            }
            return specification;
        }
        Specification<T> tail = this.components.remove(this.components.size() - 1);
        return with(tail.and(specification));
    }

    @Override
    public Specification<T> or(Specification<T> specification) {
        return with(specification);
    }

    @Override
    public Optional<Specification<T>> remainderUnsatisfiedBy(T candidate) {
        return (isSatisfiedBy(candidate))
                ? Optional.empty()
                : Optional.of(new DisjunctionSpecification<T>().with(this.components));
    }

    @Override
    public String toString() {
        return this.components.stream().map(x -> x.toString()).collect(joining(" || ", "(", ")"));
    }
}
