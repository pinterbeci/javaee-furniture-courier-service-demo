package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.MenuItem;
import hu.ulyssys.java.course.maven.service.MenuItemService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MenuItemCRUDMBean extends CoreCRUDMBean<MenuItem> implements Serializable {


    @Inject
    public MenuItemCRUDMBean(MenuItemService service, LoggedInUserBean loggedInUserBean) {
        super(service);

        if (!loggedInUserBean.isAdmin()) {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Nincs jogosultsága ehhez a művelethez!"));
            throw new SecurityException("Nincs jogosultsága ehhez a művelethez!");
        }
    }

    @Override
    protected String dialogName() {
        return "menuDialog";
    }

    @Override
    protected MenuItem initNewEntity() {
        return new MenuItem();
    }
}
