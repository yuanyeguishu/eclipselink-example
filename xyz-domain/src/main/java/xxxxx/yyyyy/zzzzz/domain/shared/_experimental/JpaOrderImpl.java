//package xxxxx.yyyyy.zzzzz.domain.shared._experimental;
//
//import static xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction.*;
//
//import java.io.Serializable;
//import javax.persistence.metamodel.SingularAttribute;
//import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
//
//public class JpaOrderImpl<T extends AggregateRoot<T, ? extends Serializable>> implements Order<T> {
//
//    private final SingularAttribute<T, ? extends Serializable> property;
//    private final Direction direction;
//
//    public JpaOrderImpl(SingularAttribute<T, ? extends Serializable> property) {
//        this(property, null);
//    }
//
//    public JpaOrderImpl(SingularAttribute<T, ? extends Serializable> property, Direction direction) {
//        if (property == null) {
//            throw new IllegalArgumentException("'property' must not be null");
//        }
//        this.property = property;
//        this.direction = (direction != null) ? direction : ASCENDING;
//    }
//
//    @Override
//    public SingularAttribute<T, ? extends Serializable> property() {
//        return this.property;
//    }
//
//    @Override
//    public Direction direction() {
//        return this.direction;
//    }
//}
