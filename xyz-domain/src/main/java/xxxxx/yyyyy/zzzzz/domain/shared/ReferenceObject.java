package xxxxx.yyyyy.zzzzz.domain.shared;

import java.io.Serializable;

public interface ReferenceObject<T extends ReferenceObject<T, ID>, ID extends Serializable> extends Serializable {

    ID id();

    Long version();

    default boolean isNew() {
        // TODO
        //return id() == null && version() == null;
        return id() == null || version() == null;
    }

    default boolean isSameIdentityAs(T other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
