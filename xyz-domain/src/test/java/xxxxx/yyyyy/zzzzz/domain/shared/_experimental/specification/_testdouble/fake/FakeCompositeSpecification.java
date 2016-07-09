package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake;

import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.AbstractCompositeSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;

@lombok.extern.slf4j.Slf4j
@lombok.EqualsAndHashCode(callSuper = true)
public class FakeCompositeSpecification<T> extends AbstractCompositeSpecification<T> {

    @Override
    public boolean isSatisfiedBy(T candidate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isGeneralizationOf(Specification<T> specification) {
        throw new UnsupportedOperationException();
    }
}
