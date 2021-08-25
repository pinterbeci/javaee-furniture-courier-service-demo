package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.CourierDAO;
import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CourierDAOImpl extends CoreDAOImpl<Courier> implements CourierDAO {
    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }

    @Override
    public void deleteCourierFromOrder(Long id) {
        TypedQuery<Courier> query =
                entityManager.createQuery("SELECT c FROM Courier c WHERE c.id = :id ORDER BY c.id",
                        getManagedClass());
        query.setParameter("id", id);
        entityManager.remove(query.getResultList().get(0));
    }

    @Override
    public Courier findById(Long id) {
        TypedQuery<Courier> query =
                entityManager.createQuery("SELECT c FROM Courier c WHERE c.id = :id ORDER BY c.id",
                        getManagedClass());
        query.setParameter("id", id);
        return query.getResultList().get(0);
    }


}
