package xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake;

import static xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction.*;

import java.io.Serializable;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Order;

@lombok.extern.slf4j.Slf4j
@lombok.EqualsAndHashCode
@lombok.ToString
public class FakeOrder<T extends AggregateRoot<T, ? extends Serializable>> implements Order<T> {

    private final String property;
    private final Direction direction;

    public FakeOrder(String property) {
        this(property, null);
    }

    public FakeOrder(String property, Direction direction) {
        if (property == null) {
            throw new IllegalArgumentException("'property' must not be null");
        }
        this.property = property;
        this.direction = (direction != null) ? direction : ASCENDING;
    }

    @Override
    public String property() {
        return this.property;
    }

    @Override
    public Direction direction() {
        return this.direction;
    }
}
