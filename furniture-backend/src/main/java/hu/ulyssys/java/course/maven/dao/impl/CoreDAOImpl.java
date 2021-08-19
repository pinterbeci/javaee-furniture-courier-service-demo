package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.CoreDAO;
import hu.ulyssys.java.course.maven.entity.AbstractEntity;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class CoreDAOImpl <T extends AbstractEntity> implements CoreDAO<T> {

    @PersistenceContext(unitName = "TestPersistence")
    protected EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public List<T> findAll() {
        return entityManager.createQuery("select n from " + getManagedClass().getSimpleName()
                + " n order by n.id", getManagedClass()).getResultList();
    }

    @Override
    public T findById(Long id) {
        return entityManager.find(getManagedClass(), id);
    }


    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }


    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    protected abstract Class<T> getManagedClass();


}
