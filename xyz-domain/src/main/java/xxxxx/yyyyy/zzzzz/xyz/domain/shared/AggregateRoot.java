package xxxxx.yyyyy.zzzzz.xyz.domain.shared;

import java.io.Serializable;

public interface AggregateRoot<T extends AggregateRoot<T, ID>, ID extends Serializable> extends ReferenceObject<T, ID> {
}
