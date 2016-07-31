package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import static java.util.stream.Collectors.*;

import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Traverser;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.ConjunctionSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.DisjunctionSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.EqualSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.ValueBoundSpecification;

public class SpecificationToCriteria<T> implements Traverser<Specification<T>, String> {

    @Override
    public String traverse(Specification<T> t) {
        Class<?> clazz = t.getClass();
        if (ConjunctionSpecification.class.isAssignableFrom(clazz)) {
            return ((ConjunctionSpecification<T>) t).unmodifiableComponents().stream()
                    .map(this::traverse)
                    .collect(joining(" and ", "(", ")"));
        } else if (DisjunctionSpecification.class.isAssignableFrom(clazz)) {
            return ((DisjunctionSpecification<T>) t).unmodifiableComponents().stream()
                    .map(this::traverse)
                    .collect(joining(" or ", "(", ")"));
        } else if (ValueBoundSpecification.class.isAssignableFrom(clazz)) {
            if (EqualSpecification.class.isAssignableFrom(clazz)) {
                EqualSpecification<T> equal = (EqualSpecification<T>) t;
                return String.format("x.%s = %s", equal.property(), equal.value());
            } else {
                throw new UnsupportedOperationException(String.format("%s", clazz));
            }
        } else {
            throw new UnsupportedOperationException(String.format("%s", clazz));
        }
    }
}
