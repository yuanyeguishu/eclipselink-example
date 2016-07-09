package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCompositeSpecification<T> implements CompositeSpecification<T> {

    protected final List<Specification<T>> components;

    public AbstractCompositeSpecification() {
        this.components = new ArrayList<>();
    }

    @Override
    public CompositeSpecification<T> with(Specification<T> specification) {
        if (specification == null) {
            throw new IllegalArgumentException("'specification' must not be null");
        }
        if (!this.components.contains(specification)) {
            this.components.add(specification);
        }
        return this;
    }

    @Override
    public List<Specification<T>> unmodifiableComponents() {
        return Collections.unmodifiableList(this.components);
    }
}
