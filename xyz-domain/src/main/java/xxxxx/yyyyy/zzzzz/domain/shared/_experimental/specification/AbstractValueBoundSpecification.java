package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import java.lang.reflect.Field;
import java.util.Optional;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Reflections;

public abstract class AbstractValueBoundSpecification<T, P, V> extends AbstractLeafSpecification<T> implements ValueBoundSpecification<T, P, V> {

    protected final P property;
    protected final V value;
    protected final Class<P> propertyClass;
    protected final Class<V> valueClass;

    public AbstractValueBoundSpecification(P property, V value) {
        if (property == null) {
            throw new IllegalArgumentException("'property' must not be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("'value' must not be null");
        }
        this.property = property;
        this.value = value;
        this.propertyClass = (Class<P>) property.getClass();
        this.valueClass = (Class<V>) value.getClass();
    }

    @Override
    public P property() {
        return this.property;
    }

    @Override
    public V value() {
        return this.value;
    }

    protected Optional<V> propertyValue(T candidate) {
        try {
            Field field = (this.propertyClass == Field.class)
                    ? (Field) property()
                    : candidate.getClass().getDeclaredField(property().toString());
            if (!this.valueClass.isAssignableFrom(field.getType())) {
                throw new IllegalArgumentException(
                        String.format("%s can not be assignable from %s", this.valueClass, field.getType()));
            }
            return Optional.ofNullable(new Reflections().get(field, candidate));
        } catch (NoSuchFieldException | SecurityException cause) {
            throw new RuntimeException(cause);
        }
    }
}
