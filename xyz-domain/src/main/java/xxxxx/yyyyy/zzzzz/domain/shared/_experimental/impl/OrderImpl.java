package xxxxx.yyyyy.zzzzz.domain.shared._experimental.impl;

import static xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction.*;

import java.io.Serializable;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Order;

public class OrderImpl<T extends AggregateRoot<T, ? extends Serializable>> implements Order<T> {

    private final String property;
    private final Direction direction;

    public OrderImpl(String property) {
        this(property, null);
    }

    public OrderImpl(String property, Direction direction) {
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
