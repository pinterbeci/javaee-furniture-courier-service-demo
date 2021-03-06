package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.dao.CourierDAO;
import hu.ulyssys.java.course.maven.entity.Courier;

import javax.ejb.Stateless;

@Stateless
public class CourierServiceImpl extends AbstractServiceImpl<Courier> implements CourierService {

    @Override
    public void deleteCourierFromOrder(Long id) {
        ((CourierDAO)dao).deleteCourierFromOrder(id);
    }
}
