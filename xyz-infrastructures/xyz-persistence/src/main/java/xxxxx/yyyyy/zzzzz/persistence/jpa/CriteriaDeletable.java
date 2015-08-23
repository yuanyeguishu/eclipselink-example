package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface CriteriaDeletable<T> {

    CriteriaDelete<T> delete(CriteriaBuilder b, CriteriaDelete<T> d, Root<T> r);
}
