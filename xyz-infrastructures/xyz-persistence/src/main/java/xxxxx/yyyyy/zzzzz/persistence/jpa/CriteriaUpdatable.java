package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface CriteriaUpdatable<T> {

    CriteriaUpdate<T> update(CriteriaBuilder b, CriteriaUpdate<T> u, Root<T> r);
}
