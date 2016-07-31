package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

public final class NegationSpecification<T> implements Specification<T> {

    private final Specification<T> wrapped;

    private NegationSpecification(Specification<T> specification) {
        if (specification == null) {
            throw new IllegalArgumentException("'specification' must not be null");
        }
        this.wrapped = specification;
    }

    public static <T> Specification<T> not(Specification<T> specification) {
        return new NegationSpecification<>(specification);
    }

    public Specification<T> unwrap() {
        return this.wrapped;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !this.wrapped.isSatisfiedBy(candidate);
    }

    @Override
    public boolean isGeneralizationOf(Specification<T> specification) {
        if (specification == null) {
            return false;
        }
        if (specification.equals(this)) {
            return true;
        }
        return !this.wrapped.isGeneralizationOf(specification);
    }

    @Override
    public String toString() {
        return String.format("!%s", this.wrapped.toString());
    }
}
