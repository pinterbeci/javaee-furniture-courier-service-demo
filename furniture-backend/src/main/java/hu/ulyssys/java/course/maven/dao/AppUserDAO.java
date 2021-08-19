package hu.ulyssys.java.course.maven.dao;

import hu.ulyssys.java.course.maven.entity.AppUser;

public interface AppUserDAO extends CoreDAO<AppUser> {

    AppUser findByUsername(String username);
}
