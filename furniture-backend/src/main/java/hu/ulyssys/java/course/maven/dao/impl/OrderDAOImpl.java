package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.OrderDAO;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderDAOImpl extends CoreDAOImpl<Order> implements OrderDAO {
    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }

    @Override
    public List<Order> findOrderByCustomerID(Long id) {
        List<Order> allOrders = getAllOrderForUser();
        return allOrders.stream().filter(o -> o.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Order findByCourierID(Long id) {
        TypedQuery<Order> query =
                entityManager.createQuery("SELECT o FROM Order o  WHERE o.courier.id = :id ORDER BY o.id",
                        getManagedClass());
        query.setParameter("id", id);
        return query.getResultList().get(0);

    }

    @Override
    public void deleteOrderByCourierID(Long id) {
        TypedQuery<Order> query =
                entityManager.createQuery("SELECT o FROM Order o WHERE o.courier.id = :id ORDER BY o.id",
                        getManagedClass());
        query.setParameter("id", id);
        entityManager.remove(query.getResultList().get(0));
    }

    @Override
    public List<Order> getAllOrderForUser() {
        TypedQuery<Order> query = entityManager
                .createQuery("SELECT o FROM Order o " +
                                " JOIN AppUser cu ON o.customer.id = cu.id " +
                                " JOIN Courier c ON o.courier.id = c.id " +
                                " LEFT JOIN Furniture f ON o.id = f.id ",
                        Order.class);

        return query.getResultList();
    }


    @Override
    public List<Order> findAllUserOrderForAdmin() {
        //admin belép a megadott rendelésekbe, és tud még
        //futárt rendelni a rendeléshez
        TypedQuery<Order> query = entityManager
                .createQuery("SELECT o FROM Order o " +
                                " JOIN AppUser cu ON o.customer.id = cu.id " +
                                " LEFT JOIN Furniture f ON o.id = f.id ",
                        Order.class);

        return query.getResultList();
    }

}
