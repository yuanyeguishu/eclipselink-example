package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

public interface Sort<T extends AggregateRoot<T, ? extends Serializable>> {

    List<Order<T>> orders();
}
