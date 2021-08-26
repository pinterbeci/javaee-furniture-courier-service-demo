package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.service.*;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Named
@ViewScoped
public class OrderCRUDMBean extends OrderAwareCRUDMBean<Order> implements Serializable {


    @Inject
    private OrderService orderService;

    @Inject
    private AppUserService appUserService;

    @Inject
    private LoggedInUserBean loggedInUserBean;

    private List<Furniture> selectedFurnitureList;

    @Inject
    private FurnitureService furnitureService;

    private Date minDate = new Date(Calendar.getInstance().getTime().getTime());

    @Inject
    public OrderCRUDMBean(OrderService service, FurnitureService furnitureService,
                          CourierService courierService, AppUserService customerService) {
        super(service, furnitureService, courierService, customerService);
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
            getSelectedEntity().setFurnitureList(getSelectedFurnitureList());
            if (getSelectedEntity().getId() == null) {
                getSelectedEntity().setPublicSpace(getSelectedEntity().getPublicSpace());
                getSelectedEntity().setNatureOfPublicSpace(getSelectedEntity().getNatureOfPublicSpace());
                getSelectedEntity().setAddressNumber(getSelectedEntity().getAddressNumber());
                getSelectedEntity().setCustomer(appUserService.findByUsername(loggedInUserBean.getLoggedInUserModel().getUsername()));
                getSelectedEntity().setCourier(getSelectedEntity().getCourier());
                getSelectedEntity().setSettlement(getSelectedEntity().getSettlement());
                getSelectedEntity().setDeliveryDate(getSelectedEntity().getDeliveryDate());
                getSelectedEntity().setCreatedDate(new Date());
                getSelectedEntity().setCreatedUserID(loggedInUserBean.getLoggedInUserModel().getUserID());


            } else {
                getSelectedEntity().setModifierUserID(loggedInUserBean.getLoggedInUserModel().getUserID());
                getSelectedEntity().setModifiedDate(new Date());
            }

            PrimeFaces.current().executeScript("PF('" + dialogName() + "').hide()");
            super.save();

            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Rendelését sikeresen feladta! Szabad futár hozzárendelése után, ellenőrizheti a rendelését a 'Rendeléseim' fül segítségével!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen törlés", null));
        }
    }

    public Date getMinDate() {
        return minDate;
    }

    @Override
    public Order getSelectedEntity() {
        Order order = super.getSelectedEntity();
        if (order != null) {
            setSelectedFurnitureList(furnitureService.findByOrderID(order.getId()));
        }
        return super.getSelectedEntity();
    }

    public List<Furniture> getSelectedFurnitureList() {
        return selectedFurnitureList;
    }

    public void setSelectedFurnitureList(List<Furniture> selectedFurnitureList) {
        this.selectedFurnitureList = selectedFurnitureList;
    }

    public List<Order> customerOrders() {
        return orderService.findOrderByCustomerID(loggedInUserBean.getLoggedInUserModel().getUserID());
    }

    public List<Order> allOrders() {
        //admin számára, futár kivételével minden adat szerepel
        return orderService.findAllUserOrderForAdmin();
    }

}