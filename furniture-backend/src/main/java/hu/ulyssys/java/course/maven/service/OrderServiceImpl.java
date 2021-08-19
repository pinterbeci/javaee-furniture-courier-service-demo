package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.dao.OrderDAO;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderServiceImpl extends AbstractServiceImpl<Order> implements OrderService {

    @Override
    public List<Order> findByCustomerID(Long id) {
        return ((OrderDAO) dao).findByCustomerID(id);
    }

}
