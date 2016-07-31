package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Test;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeBean;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeValueBoundSpecification;

@lombok.extern.slf4j.Slf4j
public class SpecificationTest {

    private static final Specification<FakeBean> A = new FakeValueBoundSpecification<>("stringValue", "A");
    private static final Specification<FakeBean> B = new FakeValueBoundSpecification<>("stringValue", "B");
    private static final Specification<FakeBean> C = new FakeValueBoundSpecification<>("stringValue", "C");
    private static final Specification<FakeBean> D = new FakeValueBoundSpecification<>("stringValue", "D");
    private static final Specification<FakeBean> E = new FakeValueBoundSpecification<>("stringValue", "E");

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_1() { // TODO should change method name
        assertThat(A.and(B).and(C).and(D).and(E).toString(), is(equalTo("(A && B && C && D && E)")));
        assertThat(A.or(B).and(C).and(D).and(E).toString(), is(equalTo("(A || (B && C && D && E))")));
        assertThat(A.and(B).or(C).and(D).and(E).toString(), is(equalTo("((A && B) || (C && D && E))")));
        assertThat(A.and(B).and(C).or(D).and(E).toString(), is(equalTo("((A && B && C) || (D && E))")));
        assertThat(A.and(B).and(C).and(D).or(E).toString(), is(equalTo("((A && B && C && D) || E)")));
        assertThat(A.or(B).or(C).and(D).and(E).toString(), is(equalTo("(A || B || (C && D && E))")));
        assertThat(A.or(B).and(C).or(D).and(E).toString(), is(equalTo("(A || (B && C) || (D && E))")));
        assertThat(A.or(B).and(C).and(D).or(E).toString(), is(equalTo("(A || (B && C && D) || E)")));
        assertThat(A.and(B).or(C).or(D).and(E).toString(), is(equalTo("((A && B) || C || (D && E))")));
        assertThat(A.and(B).or(C).and(D).or(E).toString(), is(equalTo("((A && B) || (C && D) || E)")));
        assertThat(A.and(B).and(C).or(D).or(E).toString(), is(equalTo("((A && B && C) || D || E)")));
        assertThat(A.or(B).or(C).or(D).and(E).toString(), is(equalTo("(A || B || C || (D && E))")));
        assertThat(A.or(B).or(C).and(D).or(E).toString(), is(equalTo("(A || B || (C && D) || E)")));
        assertThat(A.and(B).or(C).or(D).or(E).toString(), is(equalTo("((A && B) || C || D || E)")));
        assertThat(A.or(B).or(C).or(D).or(E).toString(), is(equalTo("(A || B || C || D || E)")));
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_2() { // TODO should change method name
        assertThat(A.remainderUnsatisfiedBy(new FakeBean("A")), is(Optional.empty()));
        assertThat(A.remainderUnsatisfiedBy(new FakeBean("B")), is(Optional.of(A)));
    }
}
