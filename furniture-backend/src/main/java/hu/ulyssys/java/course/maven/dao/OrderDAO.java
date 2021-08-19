package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Order;

import java.util.List;

public interface OrderDAO extends CoreDAO<Order> {

    List<Order> findByCustomerID(Long id);

}
