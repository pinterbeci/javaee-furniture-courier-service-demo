package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.FurnitureDAO;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.service.FurnitureService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


  @Override
    public List<Furniture> orderedFurniture() {

        TypedQuery<Furniture> query =
                entityManager.createQuery(
                        "SELECT f FROM Order o JOIN o.furnitureList f",
                        Furniture.class);
        return query.getResultList();
    }


}
