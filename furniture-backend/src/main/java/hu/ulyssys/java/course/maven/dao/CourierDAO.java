package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Courier;

public interface CourierDAO extends CoreDAO<Courier> {


    void deleteCourierFromOrder(Long id);
}
