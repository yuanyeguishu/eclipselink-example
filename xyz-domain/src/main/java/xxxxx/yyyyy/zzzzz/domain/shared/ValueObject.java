package xxxxx.yyyyy.zzzzz.domain.shared;

import java.io.Serializable;

public interface ValueObject<T extends ValueObject<T>> extends Serializable {

    default boolean isSameValueAs(final T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
