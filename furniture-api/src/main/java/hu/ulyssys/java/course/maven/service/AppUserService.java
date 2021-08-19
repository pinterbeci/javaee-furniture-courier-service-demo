package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.entity.AppUser;

public interface AppUserService extends CoreService<AppUser> {

    AppUser findByUsername(String username);
}
