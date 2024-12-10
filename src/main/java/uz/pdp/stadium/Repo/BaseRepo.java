package uz.pdp.stadium.Repo;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import uz.pdp.stadium.Data.MyListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BaseRepo<T> {
    private final Class<T> entityClass;
    public BaseRepo() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        } else {
            throw new RuntimeException("Generic type not provided");
        }
    }
    public Class<T> getEntityClass() {
        return entityClass;
    }

    public List<T> findAll() {
        try (EntityManager entityManager=MyListener.getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            List<T> resultList = entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
            entityManager.getTransaction().commit();
            return resultList;
        }
    }
    public void save(T entity) {
        try (EntityManager entityManager = MyListener.getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public T findById(int id) {
        try (EntityManager entityManager = MyListener.getEntityManagerFactory().createEntityManager()) {
            return entityManager.find(entityClass, id);
        }
    }
}
