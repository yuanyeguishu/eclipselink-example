package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import java.io.Serializable;
import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;

public interface Page<T extends AggregateRoot<T, ? extends Serializable>> {

    int number();

    int totalNumberOfPages();

    int size();

    List<T> contents();
}
