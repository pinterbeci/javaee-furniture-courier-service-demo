package hu.ulyssys.java.course.maven.dao.impl;

import hu.ulyssys.java.course.maven.dao.MenuItemDAO;
import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.MenuItem;
import hu.ulyssys.java.course.maven.entity.Order;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MenuItemDAOImpl extends CoreDAOImpl<MenuItem> implements MenuItemDAO {
    @Override
    protected Class<MenuItem> getManagedClass() {
        return MenuItem.class;
    }

}
