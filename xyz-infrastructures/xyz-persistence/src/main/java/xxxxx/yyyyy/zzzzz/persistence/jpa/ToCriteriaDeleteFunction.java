package xxxxx.yyyyy.zzzzz.persistence.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface ToCriteriaDeleteFunction<T> {

    CriteriaDelete<T> apply(CriteriaBuilder x, CriteriaDelete<T> y, Root<T> z);
}
