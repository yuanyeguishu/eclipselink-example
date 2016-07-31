package xxxxx.yyyyy.zzzzz.domain.shared._experimental._testdouble.fake;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static xxxxx.yyyyy.zzzzz.domain.shared._experimental.Direction.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import xxxxx.yyyyy.zzzzz.domain.shared.AbstractRepository;
import xxxxx.yyyyy.zzzzz.domain.shared.AggregateRoot;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Order;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Page;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Pagination;
import xxxxx.yyyyy.zzzzz.domain.shared._experimental.Sort;

public class AbstractRepositoryInMemory<T extends AggregateRoot<T, ID>, ID extends Serializable> extends AbstractRepository<T, ID> {

    protected final Map<ID, T> storage;

    public AbstractRepositoryInMemory() {
        super();
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    protected boolean isProxyClass() {
        // $Proxy$_$$_WeldSubclass
        // $Proxy$_$$_WeldClientProxy
        return getClass().getSimpleName().matches(".*\\$Proxy\\$_\\$\\$_Weld.*");
    }

    @Override
    public <U extends T> U store(U entity) {
        Objects.requireNonNull(entity);
        this.storage.put(entity.id(), entity);
        return entity;
    }

    @Override
    public <U extends T> List<U> store(List<U> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <U extends T> void remove(U entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <U extends T> void remove(List<U> entities) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T find(ID id) {
        Objects.requireNonNull(id);
        return this.storage.get(id);
    }

    @Override
    public List<T> findAll() {
        return this.storage.values().stream().collect(toList());
    }
//
//    @Override
//    public List<T> findAll(Specification<T> specification) {
//        Objects.requireNonNull(specification);
//        return this.storage.values().stream()
//                .filter(specification::isSatisfiedBy).collect(toList());
//    }

    @Override
    public List<T> findAll(Sort<T> sort) {
        //return findAll(null, sort);
        Objects.requireNonNull(sort);
        List<T> values = findAll();
        return sort(values, sort).collect(toList());
    }
//
//    @Override
//    public List<T> findAll(Specification<T> specification, Sort<T> sort) {
//        Objects.requireNonNull(sort);
//        List<T> values = (specification != null) ? findAll(specification) : findAll();
//        return sort(values, sort).collect(toList());
//    }

    @Override
    public Page<T> findAll(Pagination<T> pagination) {
        //return findAll(null, pagination);
        Objects.requireNonNull(pagination);
        List<T> values = findAll();
        if (values.size() < pagination.offset()) {
            return null;
        } else {
            List<T> contents = sort(values, pagination.sort())
                    .skip(pagination.offset())
                    .limit(pagination.offset() + pagination.size())
                    .collect(toList());
            return new FakePage<>(pagination.number(), contents);
        }
    }
//
//    @Override
//    public Page<T> findAll(Specification<T> specification, Pagination<T> pagination) {
//        Objects.requireNonNull(pagination);
//        List<T> values = (specification != null) ? findAll(specification) : findAll();
//        if (values.size() < pagination.offset()) {
//            return null;
//        } else {
//            List<T> contents = sort(values, pagination.sort())
//                    .skip(pagination.offset())
//                    .limit(pagination.offset() + pagination.size())
//                    .collect(toList());
//            return new FakePage<>(pagination.number(), contents);
//        }
//    }

    protected Stream<T> sort(List<T> values, Sort<T> sort) {
        Objects.requireNonNull(values);
        Objects.requireNonNull(sort);
        Comparator<T> comparator = null;
        if (!sort.orders().isEmpty()) {
            List<Order<T>> orders = new ArrayList<>(sort.orders());
            Collections.reverse(orders);
            for (Order<T> order : orders) {
                comparator = (comparator == null)
                        ? comparing(x -> comparable(x, (/*TODO*/String) order.property()))
                        : comparator.thenComparing(x -> comparable(x, (/*TODO*/String) order.property()));
                if (order.direction() == DESCENDING) {
                    comparator = comparator.reversed();
                }
            }
        }
        return (comparator != null)
                ? values.stream().sorted(comparator)
                : values.stream();
    }

    protected <K extends Comparable<? super K>> K comparable(T object, String property) {
        try {
            Field field = object.getClass().getDeclaredField(property);
            if (!field.isAccessible()) {
                AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                    field.setAccessible(true);
                    return null;
                });
            }
            return (K) field.get(object);
        } catch (ReflectiveOperationException | SecurityException cause) {
            throw new RuntimeException(cause);
        }
    }
}
