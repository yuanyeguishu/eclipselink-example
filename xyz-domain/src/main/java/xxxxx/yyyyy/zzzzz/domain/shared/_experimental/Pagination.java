package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

public interface Pagination<T extends AggregateRoot<T, ? extends Serializable>> {

    int number(); // 'zero-based' page number.

    int size();

    int offset();

    Sort<T> sort();
//
//    Pagination<T> previous();
//
//    Pagination<T> next();
}
