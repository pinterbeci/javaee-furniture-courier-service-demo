package hu.ulyssys.java.course.maven.mbean;


import hu.ulyssys.java.course.maven.entity.MenuItem;
import hu.ulyssys.java.course.maven.service.MenuItemService;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MenuProviderMBean {

    @Inject
    private MenuItemService service;

    @Inject
    private LoggedInUserBean loggedInUserBean;


    public MenuModel getMenuModel() {
        DefaultMenuModel menuModel = new DefaultMenuModel();
        service.getAll().forEach(menuItem -> {
            addMenuItem(menuModel, menuItem);
        });

        return menuModel;
    }

    private void addMenuItem(DefaultMenuModel menuModel, MenuItem menuItem) {

        DefaultMenuItem element = new DefaultMenuItem();
        element.setHref(menuItem.getUrl());
        element.setValue(menuItem.getLabel());

        if (Boolean.FALSE.equals(menuItem.getAdminFunction()) || menuItem.getAdminFunction() == null
                || (Boolean.TRUE.equals(menuItem.getAdminFunction()) && loggedInUserBean.isAdmin()))
            menuModel.getElements().add(element);

    }
}
