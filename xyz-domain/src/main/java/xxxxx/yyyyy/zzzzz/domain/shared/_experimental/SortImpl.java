package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
//@lombok.extern.slf4j.Slf4j
//@lombok.EqualsAndHashCode
//@lombok.ToString

public class SortImpl<T extends AggregateRoot<T, ? extends Serializable>> implements Sort<T> {

    private final List<Order<T>> orders;

    public SortImpl(List<Order<T>> orders) {
        this.orders = orders;
    }

    public SortImpl(Order<T>... orders) {
        this.orders = Arrays.asList(orders);
    }

    @Override
    public List<Order<T>> orders() {
        return this.orders;
    }
}
