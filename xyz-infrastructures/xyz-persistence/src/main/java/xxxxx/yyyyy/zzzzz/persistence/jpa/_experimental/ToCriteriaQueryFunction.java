package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface ToCriteriaQueryFunction<T> {

    CriteriaQuery<T> apply(CriteriaBuilder x, CriteriaQuery<T> y, Root<T> z);
}
