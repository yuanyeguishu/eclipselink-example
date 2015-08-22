package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface Queryable<T> {

    CriteriaQuery<T> query(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> r);
}
