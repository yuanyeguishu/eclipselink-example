package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface ToCriteriaUpdateFunction<T> {

    CriteriaUpdate<T> apply(CriteriaBuilder x, CriteriaUpdate<T> y, Root<T> z);
}
