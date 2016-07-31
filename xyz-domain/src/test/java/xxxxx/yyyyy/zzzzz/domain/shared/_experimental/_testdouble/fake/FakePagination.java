package xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake;

import java.io.Serializable;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Pagination;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Sort;

@lombok.extern.slf4j.Slf4j
@lombok.EqualsAndHashCode
@lombok.ToString
//@lombok.AllArgsConstructor
public class FakePagination<T extends AggregateRoot<T, ? extends Serializable>> implements Pagination<T> {

    private final int number;
    private final int size;
    private final Sort<T> sort;

    public FakePagination(int number, int size, Sort<T> sort) {
        if (number < 0) {
            throw new IllegalArgumentException(
                    String.format("'number' must not be less than zero (%d)", number));
        }
        if (size < 1) {
            throw new IllegalArgumentException(
                    String.format("'size' must not be less than one (%d)", size));
        }
        if (sort == null) {
            throw new IllegalArgumentException("'sort' must not be null");
        }
        this.number = number;
        this.size = size;
        this.sort = sort;
    }

    @Override
    public int number() {
        return this.number;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int offset() {
        return this.number * this.size;
    }

    @Override
    public Sort<T> sort() {
        return this.sort;
    }
}
