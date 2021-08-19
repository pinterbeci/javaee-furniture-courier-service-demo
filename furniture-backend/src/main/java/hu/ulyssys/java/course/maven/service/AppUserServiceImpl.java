package hu.ulyssys.java.course.maven.service;

import hu.ulyssys.java.course.maven.dao.AppUserDAO;
import hu.ulyssys.java.course.maven.entity.AppUser;

import javax.ejb.Stateless;

@Stateless
public class AppUserServiceImpl extends AbstractServiceImpl<AppUser> implements AppUserService {
    @Override
    public AppUser findByUsername(String username) {
        return ((AppUserDAO) dao).findByUsername(username);
    }
}
