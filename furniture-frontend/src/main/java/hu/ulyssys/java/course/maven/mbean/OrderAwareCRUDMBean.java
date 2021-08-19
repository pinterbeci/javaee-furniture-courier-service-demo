package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AbstractFurniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.mbean.CoreCRUDMBean;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.OrderService;

import java.io.Serializable;
import java.util.List;

public abstract class OrderAwareCRUDMBean<T extends AbstractFurniture> extends CoreCRUDMBean<T> implements Serializable {

    private List<Order> orderList;

    public OrderAwareCRUDMBean(CoreService<T> service, OrderService orderService) {
        super(service);
        orderList = orderService.getAll();
    }
}
