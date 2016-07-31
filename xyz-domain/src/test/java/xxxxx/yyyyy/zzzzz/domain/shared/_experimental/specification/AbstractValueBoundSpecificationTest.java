package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeBean;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification._testdouble.fake.FakeValueBoundSpecification;

@lombok.extern.slf4j.Slf4j
public class AbstractValueBoundSpecificationTest {

    private static final Specification<FakeBean> A = new FakeValueBoundSpecification<>("stringValue", "A");
    private static final Specification<FakeBean> B = new FakeValueBoundSpecification<>("stringValue", "B");
    private static final Specification<FakeBean> C = new FakeValueBoundSpecification<>("stringValue", "C");
    //
//    private static final Specification<FakeBean> X = new FakeValueBoundSpecification<>("stringValue", "X");
    //
    private static final Specification<FakeBean> A_AND_B = A.and(B);
    private static final Specification<FakeBean> A_OR_B = A.or(B);
    private static final Specification<FakeBean> A_AND_B_AND_C = A.and(B).and(C);
    private static final Specification<FakeBean> A_AND_B_OR_C = A.and(B).or(C);
    private static final Specification<FakeBean> A_OR_B_AND_C = A.or(B).and(C);
    private static final Specification<FakeBean> A_OR_B_OR_C = A.or(B).or(C);
    //
    private static final Specification<FakeBean> ABC = new FakeValueBoundSpecification<>("stringValue", "ABC");
    private static final Specification<FakeBean> AB = new FakeValueBoundSpecification<>("stringValue", "AB");
    private static final Specification<FakeBean> AC = new FakeValueBoundSpecification<>("stringValue", "AC");
    private static final Specification<FakeBean> BC = new FakeValueBoundSpecification<>("stringValue", "BC");
//    private static final Specification<FakeBean> A___ = new FakeValueBoundSpecification<>("stringValue", "A");
//    private static final Specification<FakeBean> _B__ = new FakeValueBoundSpecification<>("stringValue", "B");
//    private static final Specification<FakeBean> __C_ = new FakeValueBoundSpecification<>("stringValue", "C");
//    private static final Specification<FakeBean> ___X = new FakeValueBoundSpecification<>("stringValue", "X");

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_1() { // TODO should change method name
        //
        assertThat(A.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A.isSatisfiedBy(new FakeBean("AB")), is(true));
        assertThat(A.isSatisfiedBy(new FakeBean("BC")), is(false));
        assertThat(A.isSatisfiedBy(new FakeBean("AC")), is(true));
        assertThat(A.isSatisfiedBy(new FakeBean("A")), is(true));
        assertThat(A.isSatisfiedBy(new FakeBean("B")), is(false));
        assertThat(A.isSatisfiedBy(new FakeBean("C")), is(false));
        assertThat(A.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(ABC.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(ABC.isSatisfiedBy(new FakeBean("AB")), is(false));
        assertThat(ABC.isSatisfiedBy(new FakeBean("BC")), is(false));
        assertThat(ABC.isSatisfiedBy(new FakeBean("AC")), is(false));
        assertThat(ABC.isSatisfiedBy(new FakeBean("A")), is(false));
        assertThat(ABC.isSatisfiedBy(new FakeBean("B")), is(false));
        assertThat(ABC.isSatisfiedBy(new FakeBean("C")), is(false));
        assertThat(ABC.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("AB")), is(true));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("BC")), is(false));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("AC")), is(false));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("A")), is(false));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("B")), is(false));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("C")), is(false));
        assertThat(A_AND_B.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("AB")), is(true));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("BC")), is(true));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("AC")), is(true));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("A")), is(true));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("B")), is(true));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("C")), is(false));
        assertThat(A_OR_B.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("AB")), is(false));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("BC")), is(false));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("AC")), is(false));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("A")), is(false));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("B")), is(false));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("C")), is(false));
        assertThat(A_AND_B_AND_C.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("AB")), is(true));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("BC")), is(true));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("AC")), is(true));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("A")), is(false));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("B")), is(false));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("C")), is(true));
        assertThat(A_AND_B_OR_C.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("AB")), is(true));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("BC")), is(true));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("AC")), is(true));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("A")), is(true));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("B")), is(false));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("C")), is(false));
        assertThat(A_OR_B_AND_C.isSatisfiedBy(new FakeBean("")), is(false));
        //
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("ABC")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("AB")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("BC")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("AC")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("A")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("B")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("C")), is(true));
        assertThat(A_OR_B_OR_C.isSatisfiedBy(new FakeBean("")), is(false));
    }

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_2() { // TODO should change method name
        //
        assertThat(A.isGeneralizationOf(A), is(true));
        assertThat(A.isGeneralizationOf(B), is(false));
        assertThat(A.isGeneralizationOf(C), is(false));
        assertThat(A.isGeneralizationOf(A_AND_B), is(true));
        assertThat(A.isGeneralizationOf(A_OR_B), is(false));
        assertThat(A.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A.isGeneralizationOf(A_AND_B_OR_C), is(false));
        assertThat(A.isGeneralizationOf(A_OR_B_AND_C), is(false));
        assertThat(A.isGeneralizationOf(A_OR_B_OR_C), is(false));
        assertThat(A.isGeneralizationOf(ABC), is(false));
        assertThat(A.isGeneralizationOf(AB), is(false));
        assertThat(A.isGeneralizationOf(AC), is(false));
        assertThat(A.isGeneralizationOf(BC), is(false));
        //
        assertThat(ABC.isGeneralizationOf(A), is(true));
        assertThat(ABC.isGeneralizationOf(B), is(true));
        assertThat(ABC.isGeneralizationOf(C), is(true));
        assertThat(ABC.isGeneralizationOf(A_AND_B), is(true));
        assertThat(ABC.isGeneralizationOf(A_OR_B), is(true));
        assertThat(ABC.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(ABC.isGeneralizationOf(A_AND_B_OR_C), is(true));
        assertThat(ABC.isGeneralizationOf(A_OR_B_AND_C), is(true));
        assertThat(ABC.isGeneralizationOf(A_OR_B_OR_C), is(true));
        assertThat(ABC.isGeneralizationOf(ABC), is(true));
        assertThat(ABC.isGeneralizationOf(AB), is(true));
        assertThat(ABC.isGeneralizationOf(AC), is(true));
        assertThat(ABC.isGeneralizationOf(BC), is(true));
        //
        assertThat(A_AND_B.isGeneralizationOf(A), is(false));
        assertThat(A_AND_B.isGeneralizationOf(B), is(false));
        assertThat(A_AND_B.isGeneralizationOf(C), is(false));
        assertThat(A_AND_B.isGeneralizationOf(A_AND_B), is(true));
        assertThat(A_AND_B.isGeneralizationOf(A_OR_B), is(false));
        assertThat(A_AND_B.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A_AND_B.isGeneralizationOf(A_AND_B_OR_C), is(false));
        assertThat(A_AND_B.isGeneralizationOf(A_OR_B_AND_C), is(false));
        assertThat(A_AND_B.isGeneralizationOf(A_OR_B_OR_C), is(false));
        assertThat(A_AND_B.isGeneralizationOf(ABC), is(false));
        assertThat(A_AND_B.isGeneralizationOf(AB), is(false));
        assertThat(A_AND_B.isGeneralizationOf(AC), is(false));
        assertThat(A_AND_B.isGeneralizationOf(BC), is(false));
        //
        assertThat(A_OR_B.isGeneralizationOf(A), is(true));
        assertThat(A_OR_B.isGeneralizationOf(B), is(true));
        assertThat(A_OR_B.isGeneralizationOf(C), is(false));
        assertThat(A_OR_B.isGeneralizationOf(A_AND_B), is(true));
        assertThat(A_OR_B.isGeneralizationOf(A_OR_B), is(true));
        assertThat(A_OR_B.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A_OR_B.isGeneralizationOf(A_AND_B_OR_C), is(false));
        assertThat(A_OR_B.isGeneralizationOf(A_OR_B_AND_C), is(false));
        assertThat(A_OR_B.isGeneralizationOf(A_OR_B_OR_C), is(false));
        assertThat(A_OR_B.isGeneralizationOf(ABC), is(false));
        assertThat(A_OR_B.isGeneralizationOf(AB), is(false));
        assertThat(A_OR_B.isGeneralizationOf(AC), is(false));
        assertThat(A_OR_B.isGeneralizationOf(BC), is(false));
        //
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(B), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(C), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A_AND_B), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A_OR_B), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A_AND_B_OR_C), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A_OR_B_AND_C), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(A_OR_B_OR_C), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(ABC), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(AB), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(AC), is(false));
        assertThat(A_AND_B_AND_C.isGeneralizationOf(BC), is(false));
        //
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(B), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(C), is(true));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A_AND_B), is(true));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A_OR_B), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A_AND_B_OR_C), is(true));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A_OR_B_AND_C), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(A_OR_B_OR_C), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(ABC), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(AB), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(AC), is(false));
        assertThat(A_AND_B_OR_C.isGeneralizationOf(BC), is(false));
        //
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A), is(true));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(B), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(C), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A_AND_B), is(true));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A_OR_B), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A_AND_B_OR_C), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A_OR_B_AND_C), is(true));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(A_OR_B_OR_C), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(ABC), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(AB), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(AC), is(false));
        assertThat(A_OR_B_AND_C.isGeneralizationOf(BC), is(false));
        //
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A), is(true));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(B), is(true));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(C), is(true));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A_AND_B), is(true));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A_OR_B), is(false));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A_AND_B_AND_C), is(true));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A_AND_B_OR_C), is(false));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A_OR_B_AND_C), is(false));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(A_OR_B_OR_C), is(true));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(ABC), is(false));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(AB), is(false));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(AC), is(false));
        assertThat(A_OR_B_OR_C.isGeneralizationOf(BC), is(false));
    }
}
