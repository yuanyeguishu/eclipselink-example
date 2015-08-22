package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface Deletable<T> {

    CriteriaQuery<T> delete(CriteriaBuilder cb, CriteriaDelete<T> cd, Root<T> r);
}
