package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.entity.Order;

import java.util.List;


public interface OrderService extends CoreService<Order> {
    List<Order> findByCustomerID(Long id);
}
