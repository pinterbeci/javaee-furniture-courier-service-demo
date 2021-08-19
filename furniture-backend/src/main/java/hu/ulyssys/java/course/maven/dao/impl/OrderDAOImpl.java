package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.OrderDAO;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class OrderDAOImpl extends CoreDAOImpl<Order> implements OrderDAO {
    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }

    @Override
    public List<Order> findByCustomerID(Long id) {
        TypedQuery<Order> query =
                entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :id ORDER BY o.id",
                        getManagedClass());
        query.setParameter("id", id);
        return query.getResultList();
    }

}
