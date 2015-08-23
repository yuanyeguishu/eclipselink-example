package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface CriteriaQueryable<T> {

    CriteriaQuery<T> query(CriteriaBuilder b, CriteriaQuery<T> q, Root<T> r);
}
