package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.AbstractEntity;

import java.util.List;

public interface CoreDAO<T extends AbstractEntity> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    T update(T entity);

    void delete(Long id);

}
