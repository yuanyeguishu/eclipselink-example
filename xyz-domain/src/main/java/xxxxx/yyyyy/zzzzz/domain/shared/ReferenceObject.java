package xxxxx.yyyyy.zzzzz.domain.shared;

import java.io.Serializable;

public interface ReferenceObject<T extends ReferenceObject<T, ID>, ID extends Serializable> extends Serializable {

    ID getId();

    Long getVersion();

    default boolean isNew() {
        return getId() == null || getVersion() == null;
    }

    default boolean isSameIdentityAs(final T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
