package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeBean;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeCompositeSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeValueBoundSpecification;

@lombok.extern.slf4j.Slf4j
public class AbstractCompositeSpecificationTest {

    private static final Specification<FakeBean> A = new FakeValueBoundSpecification<>("stringValue", "A");
    private static final Specification<FakeBean> B = new FakeValueBoundSpecification<>("stringValue", "B");

    @Test(expected = IllegalArgumentException.class)
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_1() { // TODO should change method name
        FakeCompositeSpecification<FakeBean> composite = new FakeCompositeSpecification<>();
        Specification<FakeBean> component = null;
        composite.with(component);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_2() { // TODO should change method name
        FakeCompositeSpecification<FakeBean> composite = new FakeCompositeSpecification<>();
        List<Specification<FakeBean>> components = null;
        composite.with(components);
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_3() { // TODO should change method name
        FakeCompositeSpecification<FakeBean> composite = new FakeCompositeSpecification<>();
        assertThat(A.equals(A), is(true));
        composite.with(A);
        composite.with(A);
        assertThat(composite.components.size(), is(1));
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_4() { // TODO should change method name
        FakeCompositeSpecification<FakeBean> composite = new FakeCompositeSpecification<>();
        assertThat(A.equals(B), is(false));
        composite.with(A);
        composite.with(B);
        assertThat(composite.components.size(), is(2));
    }
}
