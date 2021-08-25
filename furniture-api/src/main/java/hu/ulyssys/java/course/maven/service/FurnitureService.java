package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.entity.Furniture;

import java.util.List;

public interface FurnitureService extends CoreService<Furniture> {

    List<Furniture> findByOrderID(Long id);

    List<Furniture> orderedFurniture();
}
