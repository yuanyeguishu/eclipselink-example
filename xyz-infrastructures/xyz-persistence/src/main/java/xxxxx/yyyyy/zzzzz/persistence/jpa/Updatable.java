package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface Updatable<T> {

    CriteriaQuery<T> update(CriteriaBuilder cb, CriteriaUpdate<T> cu, Root<T> r);
}
