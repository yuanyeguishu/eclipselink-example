package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;

@FunctionalInterface
public interface TernaryFunction<T, U, V, R> {

    R apply(T t, U u, V v);
}
