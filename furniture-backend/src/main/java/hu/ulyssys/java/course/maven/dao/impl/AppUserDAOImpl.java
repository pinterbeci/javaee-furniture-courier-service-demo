package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.AppUserDAO;
import hu.ulyssys.java.course.maven.entity.AppUser;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AppUserDAOImpl extends CoreDAOImpl<AppUser> implements AppUserDAO {

    @Override
    protected Class<AppUser> getManagedClass() {
        return AppUser.class;
    }

    @Override
    public AppUser findByUsername(String username) {
        TypedQuery<AppUser> query = entityManager.createNamedQuery(AppUser.FIND_BY_USERNAME, AppUser.class);
        query.setParameter("username", username);
        List<AppUser> appUserList = query.getResultList();
        if (appUserList.isEmpty()) {
            return null;
        }
        return appUserList.stream().findFirst().get();
    }
}
