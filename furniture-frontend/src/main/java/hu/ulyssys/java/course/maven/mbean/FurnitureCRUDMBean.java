package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.FurnitureService;
import hu.ulyssys.java.course.maven.service.OrderService;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ViewScoped
public class FurnitureCRUDMBean extends CoreCRUDMBean<Furniture> implements Serializable {

    @Inject
    private LoggedInUserBean loggedInUserBean;

    @Inject
    public FurnitureCRUDMBean(FurnitureService service) {
        super(service);
    }


    @Override
    protected String dialogName() {
        return "furnitureDialog";
    }

    @Override
    protected Furniture initNewEntity() {
        return new Furniture();
    }

    @Override
    public void save() {
        try {
            if (getSelectedEntity().getId() == null) {
                getSelectedEntity().setPrice(getSelectedEntity().getPrice());
                getSelectedEntity().setFurnitureName(getSelectedEntity().getFurnitureName());
                getSelectedEntity().setDescription(getSelectedEntity().getDescription());
                getSelectedEntity().setCreatedUserID(loggedInUserBean.getLoggedInUserModel().getUserID());
            } else {
                getSelectedEntity().setModifierUserID(loggedInUserBean.getLoggedInUserModel().getUserID());
                getSelectedEntity().setModifiedDate(new Date());
                service.update(getSelectedEntity());
            }
            super.save();
            PrimeFaces.current().executeScript("PF('" + dialogName() + "').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen törlés", null));
        }
    }
}
