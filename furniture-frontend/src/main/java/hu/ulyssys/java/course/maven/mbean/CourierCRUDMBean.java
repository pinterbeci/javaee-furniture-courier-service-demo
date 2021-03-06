package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.CourierService;
import hu.ulyssys.java.course.maven.service.OrderService;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CourierCRUDMBean extends CoreCRUDMBean<Courier> implements Serializable {

    @Inject
    private LoggedInUserBean loggedInUserBean;

    @Inject
    public CourierCRUDMBean(CourierService service) {
        super(service);
    }


    @Override
    public void save() {
        try {
            if (getSelectedEntity().getId() == null) {
                getSelectedEntity().setLastName(getSelectedEntity().getLastName());
                getSelectedEntity().setFirstName(getSelectedEntity().getFirstName());
                getSelectedEntity().setPhoneNumber(getSelectedEntity().getPhoneNumber());
                getSelectedEntity().setCreatedUserID(loggedInUserBean.getLoggedInUserModel().getUserID());
                getSelectedEntity().setCreatedDate(new Date());
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

    @Override
    protected String dialogName() {
        return "courierDialog";
    }

    @Override
    protected Courier initNewEntity() {
        return new Courier();
    }


}
