package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeBean;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeValueBoundSpecification;

@lombok.extern.slf4j.Slf4j
public class NegationSpecificationTest {

    private static final Specification<FakeBean> A = new FakeValueBoundSpecification<>("stringValue", "A");
    private static final Specification<FakeBean> B = new FakeValueBoundSpecification<>("stringValue", "B");
    private static final Specification<FakeBean> Z = new FakeValueBoundSpecification<>("stringValue", "Z");
    private static final Specification<FakeBean> AB = new FakeValueBoundSpecification<>("stringValue", "AB");

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_1() { // TODO should change method name
        Specification<FakeBean> negation = NegationSpecification.not(A);
        Specification<FakeBean> wrapped = ((NegationSpecification<FakeBean>) negation).unwrap();
        assertThat(wrapped, is(A));
        wrapped = B;
        assertThat(wrapped, is(B));
        assertThat(((NegationSpecification<FakeBean>) negation).unwrap(), is(A));
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_2() { // TODO should change method name
        Specification<FakeBean> negation = NegationSpecification.not(A);
        assertThat(negation.isSatisfiedBy(new FakeBean("A")), is(false));
        assertThat(negation.isSatisfiedBy(new FakeBean("B")), is(true));
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_3() { // TODO should change method name
        Specification<FakeBean> negation = NegationSpecification.not(AB);
        assertThat(negation.isGeneralizationOf(A), is(false));
        assertThat(negation.isGeneralizationOf(B), is(false));
        assertThat(negation.isGeneralizationOf(Z), is(true));
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_4() { // TODO should change method name
        assertThat(NegationSpecification.not(A).toString(), is(equalTo("!A")));
        assertThat(NegationSpecification.not(A.and(B)).toString(), is(equalTo("!(A && B)")));
        assertThat(NegationSpecification.not(A.or(B)).toString(), is(equalTo("!(A || B)")));
    }
}
