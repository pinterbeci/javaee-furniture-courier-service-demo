package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.FurnitureDAO;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FurnitureDAOImpl extends CoreDAOImpl<Furniture> implements FurnitureDAO {
    @Override
    protected Class<Furniture> getManagedClass() {
        return Furniture.class;
    }

    @Override
    public List<Furniture> findByOrderID(Long id) {
        TypedQuery<Furniture> query =
                entityManager.createQuery("SELECT f FROM Furniture f WHERE f.id = :id ORDER BY f.id",
                        getManagedClass());
        query.setParameter("id", id);
        return query.getResultList();
    }
}
