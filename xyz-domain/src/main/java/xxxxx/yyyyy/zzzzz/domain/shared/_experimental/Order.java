package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

public interface Order<T extends AggregateRoot<T, ? extends Serializable>> {

    String property();

    Direction direction();
}
