package edu.globant.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable>
    implements GenericDAO<T, ID> {

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public T findById(ID id) {
        return findById(id, LockModeType.NONE);
    }

    public T findById(ID id, LockModeType lockModeType) {
        return em.find(entityClass, id, lockModeType);
    }

    public T findReferenceById(ID id) {
        return em.getReference(entityClass, id);
    }

    @SuppressWarnings("unchecked")
	public List<T> findAll() {
        return em.createQuery("SELECT i FROM "+entityClass+" i").getResultList();
    }

    public Long getCount() {
        return (Long)em.createQuery("SELECT count(i) FROM "+entityClass+" i").getSingleResult();
    }

    public T makePersistent(T instance) {
        // merge() handles transient AND detached instances
        return em.merge(instance);
    }

    public void makeTransient(T instance) {
        em.remove(instance);
    }

    
    public void checkVersion(T entity, boolean forceUpdate) {
    	/*
    	 * OPTIMISTIC_FORCE_INCREMENT force the entity Item to increment, in order to avoid to concurrent
    	 * bid for the same Item be sucessful, the first commit wins, that means that the second commit will
    	 * throw an OptimisticLockException 
    	 * */
        em.lock(
            entity,
            forceUpdate
                ? LockModeType.OPTIMISTIC_FORCE_INCREMENT
                : LockModeType.OPTIMISTIC
        );
    }
}
