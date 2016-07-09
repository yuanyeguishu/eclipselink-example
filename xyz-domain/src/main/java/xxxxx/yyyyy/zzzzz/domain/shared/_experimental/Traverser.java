package xxxxx.yyyyy.zzzzz.domain.shared._experimental;

@FunctionalInterface
public interface Traverser<T /*extends Traversable*/, R> {

    R traverse(T t);
}
