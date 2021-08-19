package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.service.AppUserService;
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
public class OrderCRUDMBean extends AbstractOrderCRUDMBean<Order> implements Serializable {

    @Inject
    public OrderCRUDMBean(OrderService service, AppUserService customerService, FurnitureService furnitureService) {
        super(service, customerService, furnitureService);
    }

    @Override
    protected String dialogName() {
        return "orderDialog";
    }

    @Override
    protected Order initNewEntity() {
        return new Order();
    }

    @Override
    public void save() {
        try {
            if (getSelectedEntity().getId() == null) {
                getSelectedEntity().setPublicSpace(getSelectedEntity().getPublicSpace());
                getSelectedEntity().setNatureOfPublicSpace(getSelectedEntity().getNatureOfPublicSpace());
                getSelectedEntity().setAddressNumber(getSelectedEntity().getAddressNumber());
                getSelectedEntity().setCustomer(getSelectedEntity().getCustomer());
                getSelectedEntity().setCourier(getSelectedEntity().getCourier());
                getSelectedEntity().setSettlement(getSelectedEntity().getSettlement());
                getSelectedEntity().setDeliveryDate(getSelectedEntity().getDeliveryDate());
                getSelectedEntity().setCreatedDate(new Date());
                //todo user hozzárendelés, ha van belépés
                getSelectedEntity().setCreatedUser(AppUserRole.USER);

            } else {
                //todo itt is :D
                getSelectedEntity().setModifierUser(AppUserRole.USER);
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
