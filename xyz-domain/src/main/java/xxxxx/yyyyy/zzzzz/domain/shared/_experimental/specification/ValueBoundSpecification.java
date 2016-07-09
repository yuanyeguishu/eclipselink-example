package xxxxx.yyyyy.zzzzz.domain.shared._experimental.specification;

public interface ValueBoundSpecification<T, P, V> extends LeafSpecification<T> {

    P property();

    V value();
}
