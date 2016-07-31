package xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake;

import java.io.Serializable;
import java.util.List;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Page;

@lombok.extern.slf4j.Slf4j
@lombok.EqualsAndHashCode
@lombok.ToString
//@lombok.AllArgsConstructor
public class FakePage<T extends AggregateRoot<T, ? extends Serializable>> implements Page<T> {

    private final int number;
    private final List<T> contents;

    public FakePage(int number, List<T> contents) {
        if (number < 0) {
            throw new IllegalArgumentException(String.format("number %d", number));
        }
        if (contents == null) {
            throw new IllegalArgumentException("'contents' must not be null");
        }
        this.number = number;
        this.contents = contents;
    }

    @Override
    public int number() {
        return this.number;
    }

    @Override
    public int totalNumberOfPages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        return this.contents.size();
    }

    @Override
    public List<T> contents() {
        return this.contents;
    }
}
