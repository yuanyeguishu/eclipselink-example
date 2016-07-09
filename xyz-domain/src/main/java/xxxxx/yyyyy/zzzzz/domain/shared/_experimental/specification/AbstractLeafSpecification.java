package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

public abstract class AbstractLeafSpecification<T> implements LeafSpecification<T> {

    @Override
    public boolean isGeneralizationOf(Specification<T> specification) {
        if (specification == null) {
            return false;
        }
        if (specification.equals(this)) {
            return true;
        }
        Class<?> clazz = specification.getClass();
        if (ConjunctionSpecification.class.isAssignableFrom(clazz)) {
            return isGeneralizationOf((ConjunctionSpecification<T>) specification);
        } else if (DisjunctionSpecification.class.isAssignableFrom(clazz)) {
            return isGeneralizationOf((DisjunctionSpecification<T>) specification);
        } else if (NegationSpecification.class.isAssignableFrom(clazz)) {
            return isGeneralizationOf((NegationSpecification<T>) specification);
        } else if (LeafSpecification.class.isAssignableFrom(clazz)) {
            return isGeneralizationOf((LeafSpecification<T>) specification);
        }
        throw new UnsupportedOperationException(String.format("%s", clazz));
    }

    protected boolean isGeneralizationOf(ConjunctionSpecification<T> specification) {
        return specification.components.stream().anyMatch(this::isGeneralizationOf);
    }

    protected boolean isGeneralizationOf(DisjunctionSpecification<T> specification) {
        return specification.components.stream().allMatch(this::isGeneralizationOf);
    }

    protected boolean isGeneralizationOf(NegationSpecification<T> specification) {
        return !isGeneralizationOf(specification.unwrap());
    }

    protected abstract boolean isGeneralizationOf(LeafSpecification<T> specification);
}
