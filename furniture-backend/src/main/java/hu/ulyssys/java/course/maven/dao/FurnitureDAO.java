package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.Furniture;

import java.util.List;

public interface FurnitureDAO extends CoreDAO<Furniture> {

    List<Furniture> findByOrderID(Long id);
}
