package xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Order;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Sort;

public class FakeSort<T extends AggregateRoot<T, ? extends Serializable>> implements Sort<T> {

    private final List<Order<T>> orders;

    public FakeSort(List<Order<T>> orders) {
        this.orders = orders;
    }

    public FakeSort(Order<T>... orders) {
        this.orders = Arrays.asList(orders);
    }

    @Override
    public List<Order<T>> orders() {
        return this.orders;
    }
}
