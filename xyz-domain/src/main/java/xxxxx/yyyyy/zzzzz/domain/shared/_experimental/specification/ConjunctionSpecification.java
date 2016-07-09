package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Optional;

public final class ConjunctionSpecification<T> extends AbstractCompositeSpecification<T> {

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return this.components.stream().allMatch(x -> x.isSatisfiedBy(candidate));
    }

    @Override
    public boolean isGeneralizationOf(Specification<T> specification) {
        if (specification == null) {
            return false;
        }
        if (specification.equals(this)) {
            return true;
        }
        return this.components.stream().allMatch(x -> x.isGeneralizationOf(specification));
    }

    @Override
    public Specification<T> and(Specification<T> specification) {
        return with(specification);
    }

    @Override
    public Specification<T> or(Specification<T> specification) {
        if (this.components.isEmpty()) {
            if (specification == null) {
                throw new IllegalArgumentException("'specification' must not be null");
            }
            return specification;
        }
        return new DisjunctionSpecification<T>().with(this).with(specification);
    }

    @Override
    public Optional<Specification<T>> remainderUnsatisfiedBy(T candidate) {
        List<Specification<T>> remainders
                = this.components.stream().filter(x -> !x.isSatisfiedBy(candidate)).collect(toList());
        return (remainders.isEmpty())
                ? Optional.empty()
                : Optional.of(new ConjunctionSpecification<T>().with(remainders));
    }

    @Override
    public String toString() {
        return this.components.stream().map(x -> x.toString()).collect(joining(" && ", "(", ")"));
    }
}
