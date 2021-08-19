package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.entity.Order;

public interface CourierService extends CoreService<Courier> {

    Courier findByOwnerID(Long id);
}
