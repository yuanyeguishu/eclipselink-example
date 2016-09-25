//package xxxxx.yyyyy.zzzzz.xyz.infrastructures.persistence.jpa._experimental;
//
//import java.io.Serializable;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaDelete;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.CriteriaUpdate;
//import javax.persistence.criteria.Root;
//import xxxxx.yyyyy.zzzzz.xyz.domain.shared.AggregateRoot;
//
//public interface Functional<T extends AggregateRoot<T, ID>, ID extends Serializable> {
//
//    default T singleResult(EntityManager entityManager, Class<T> entityClass, ToCriteriaQueryFunction<T> f) {
//        CriteriaBuilder x = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> y = x.createQuery(entityClass);
//        Root<T> z = y.from(entityClass);
//        return entityManager.createQuery(f.apply(x, y, z)).getSingleResult();
//    }
//
//    default List<T> resultList(EntityManager entityManager, Class<T> entityClass, ToCriteriaQueryFunction<T> f) {
//        CriteriaBuilder x = entityManager.getCriteriaBuilder();
//        CriteriaQuery<T> y = x.createQuery(entityClass);
//        Root<T> z = y.from(entityClass);
//        return entityManager.createQuery(f.apply(x, y, z)).getResultList();
//    }
//
//    default int bulkUpdate(EntityManager entityManager, Class<T> entityClass, ToCriteriaUpdateFunction<T> f) {
//        CriteriaBuilder x = entityManager.getCriteriaBuilder();
//        CriteriaUpdate<T> y = x.createCriteriaUpdate(entityClass);
//        Root<T> z = y.from(entityClass);
//        int count = entityManager.createQuery(f.apply(x, y, z)).executeUpdate();
////        entityManager.flush();
////        entityManager.clear();
////        entityManager.getEntityManagerFactory().getCache().evictAll();
//        return count;
//    }
//
//    default int bulkDelete(EntityManager entityManager, Class<T> entityClass, ToCriteriaDeleteFunction<T> f) {
//        CriteriaBuilder x = entityManager.getCriteriaBuilder();
//        CriteriaDelete<T> y = x.createCriteriaDelete(entityClass);
//        Root<T> z = y.from(entityClass);
//        int count = entityManager.createQuery(f.apply(x, y, z)).executeUpdate();
////        entityManager.flush();
////        entityManager.clear();
////        entityManager.getEntityManagerFactory().getCache().evictAll();
//        return count;
//    }
//}
