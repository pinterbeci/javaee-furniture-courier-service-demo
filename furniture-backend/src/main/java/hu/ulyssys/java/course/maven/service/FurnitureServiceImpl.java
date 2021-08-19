package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.dao.FurnitureDAO;
import hu.ulyssys.java.course.maven.entity.Furniture;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class FurnitureServiceImpl extends AbstractServiceImpl<Furniture> implements FurnitureService {
    @Override
    public List<Furniture> findByOrderID(Long id) {
        return ((FurnitureDAO) dao).findByOrderID(id);
    }
}
