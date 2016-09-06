package xxxxx.yyyyy.zzzzz.xyz.domain.shared;

import java.io.Serializable;

public interface ValueObject<T extends ValueObject<T>> extends Serializable {

    default boolean isSameValueAs(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
