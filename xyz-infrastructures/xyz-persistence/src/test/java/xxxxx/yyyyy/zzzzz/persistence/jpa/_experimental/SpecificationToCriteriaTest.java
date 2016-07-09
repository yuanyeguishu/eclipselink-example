package xxxxx.yyyyy.zzzzz.persistence.jpa._experimental;

import org.junit.Test;
import xxxxx.yyyyy.zzzzz.domain.model.Sample;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.EqualSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;

public class SpecificationToCriteriaTest {

    private static final Specification<Sample> ID_EQUAL_1L = new EqualSpecification<>("id", 1L);
    private static final Specification<Sample> ID_EQUAL_2L = new EqualSpecification<>("id", 2L);
    private static final Specification<Sample> ID_EQUAL_3L = new EqualSpecification<>("id", 3L);

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_1() { // TODO should change method name
        SpecificationToCriteria<Sample> traverser = new SpecificationToCriteria<>();
        Specification<Sample> specification = ID_EQUAL_1L.and(ID_EQUAL_2L).or(ID_EQUAL_3L);
        System.out.println(String.format("select x from %s as x where %s", Sample.class.getName(), traverser.traverse(specification)));
    }
}
