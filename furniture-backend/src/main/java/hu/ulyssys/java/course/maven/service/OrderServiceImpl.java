package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.dao.CoreDAO;
import hu.ulyssys.java.course.maven.dao.OrderDAO;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderServiceImpl extends AbstractServiceImpl<Order> implements OrderService {

    @Override
    public List<Order> findOrderByCustomerID(Long id) {
        return ((OrderDAO) dao).findOrderByCustomerID(id);
    }

    @Override
    public Order findByCourierID(Long id) {
        return ((OrderDAO) dao).findByCourierID(id);
    }

    @Override
    public void deleteOrderByCourierID(Long id) {
        ((OrderDAO) dao).deleteOrderByCourierID(id);
    }

    @Override
    public List<Order> getAllOrderForUser() {
        return ((OrderDAO) dao).getAllOrderForUser();
    }

    @Override
    public List<Order> findAllUserOrderForAdmin() {
        return ((OrderDAO) dao).findAllUserOrderForAdmin();
    }
}
