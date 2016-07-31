package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import java.util.Objects;

@lombok.EqualsAndHashCode(callSuper = true)
@lombok.ToString
public class EqualSpecification<T> extends AbstractValueBoundSpecification<T, String, Object> {

    public EqualSpecification(String property, Object value) {
        super(property, value);
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return Objects.deepEquals(this.value, candidate);
    }

    @Override
    protected boolean isGeneralizationOf(LeafSpecification<T> specification) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
