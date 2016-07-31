package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake;

import java.util.Optional;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.AbstractValueBoundSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.LeafSpecification;

@lombok.extern.slf4j.Slf4j
@lombok.EqualsAndHashCode(callSuper = true)
public class FakeValueBoundSpecification<T> extends AbstractValueBoundSpecification<T, String, String> {

    public FakeValueBoundSpecification(String property, String value) {
        super(property, value);
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        Optional<String> propertyValue = propertyValue(candidate);
        boolean result = (propertyValue.isPresent())
                ? this.value.codePoints().allMatch(x -> propertyValue.get().codePoints().anyMatch(y -> x == y))
                : false;
        return result;
    }

    @Override
    protected boolean isGeneralizationOf(LeafSpecification<T> specification) {
        if (specification instanceof FakeValueBoundSpecification) {
            FakeValueBoundSpecification<T> that = (FakeValueBoundSpecification<T>) specification;
            boolean result = that.value.codePoints().allMatch(x -> this.value.codePoints().anyMatch(y -> x == y));
            return result;
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }
}
