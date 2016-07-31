package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake.FakeEntity;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake.FakeEntityRepository;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake.FakeOrder;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake.FakePagination;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake.FakeSort;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.AbstractLeafSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.LeafSpecification;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification.Specification;

@lombok.extern.slf4j.Slf4j
public class PageableRepositoryTest {

    @Test
    public void UnitOfWork_ExpectedBehavior_StateUnderTest_1() { // TODO should change method name
        FakeEntityRepository repository = new FakeEntityRepository();
        repository.store(new FakeEntity(1L, "C"));
        repository.store(new FakeEntity(2L, "C"));
        repository.store(new FakeEntity(3L, "C"));
        repository.store(new FakeEntity(4L, "C"));
        repository.store(new FakeEntity(5L, "C"));
        repository.store(new FakeEntity(6L, "B"));
        repository.store(new FakeEntity(7L, "B"));
        repository.store(new FakeEntity(8L, "B"));
        repository.store(new FakeEntity(9L, "B"));
        repository.store(new FakeEntity(10L, "B"));
        repository.store(new FakeEntity(11L, "A"));
        repository.store(new FakeEntity(12L, "A"));
        repository.store(new FakeEntity(13L, "A"));
        repository.store(new FakeEntity(14L, "A"));
        repository.store(new FakeEntity(15L, "A"));
        Order<FakeEntity>[] orders = new Order[]{new FakeOrder<>("name", Direction.ASCENDING), new FakeOrder<>("id", Direction.DESCENDING),};
        Sort<FakeEntity> sort = new FakeSort<>(orders);
        Pagination<FakeEntity> pagination = new FakePagination<>(0, 5, sort);
        Specification<FakeEntity> specification = new FakeSpecification();
        Page<FakeEntity> page = repository.findAll(specification, pagination);
        List<FakeEntity> contents = page.contents();
        assertThat(contents.get(0).id(), is(14L));
        assertThat(contents.get(1).id(), is(12L));
        assertThat(contents.get(2).id(), is(10L));
        assertThat(contents.get(3).id(), is(8L));
        assertThat(contents.get(4).id(), is(6L));
        contents.stream().forEach(x -> log.info("{}", x));
    }

    private static class FakeSpecification extends AbstractLeafSpecification<FakeEntity> {

        @Override
        protected boolean isGeneralizationOf(LeafSpecification<FakeEntity> specification) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isSatisfiedBy(FakeEntity candidate) {
            // ID is even number.
            return candidate.id() % 2 == 0;
        }
    }
}
