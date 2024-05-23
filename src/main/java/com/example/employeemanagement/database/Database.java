package com.example.employeemanagement.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;

public class Database {
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("EmployeeManagementPU");
        } catch (Exception e) {
            // Log the exception and handle initialization failure
            e.printStackTrace();
            // Provide a fallback mechanism or rethrow a custom exception
            throw new RuntimeException("Failed to initialize EntityManagerFactory", e);
        }
    }

    public static void addObj(Object obj) {
        executeInTransaction(em -> em.persist(obj));
    }

    public static void deleteObj(Object obj) {
        executeInTransaction(em -> em.remove(em.merge(obj)));
    }

    public static <T> T getObjectById(Class<T> clazz, long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static void updateObj(Object obj) {
        executeInTransaction(em -> em.merge(obj));
    }

    public static <T> T findObjectByCriteria(Class<T> clazz, Map<String, Object> criteria) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(clazz);
            Root<T> root = query.from(clazz);

            Predicate[] predicates = criteria.entrySet().stream()
                    .map(entry -> cb.equal(root.get(entry.getKey()), entry.getValue()))
                    .toArray(Predicate[]::new);

            query.select(root).where(predicates);

            return em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    public static <T> List<T> findAll(Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(clazz);
            Root<T> root = query.from(clazz);
            query.select(root);

            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    private static void executeInTransaction(EntityManagerAction action) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            action.execute(em);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @FunctionalInterface
    private interface EntityManagerAction {
        void execute(EntityManager em);
    }

}
