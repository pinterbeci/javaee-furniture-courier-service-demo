package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.*;
import hu.ulyssys.java.course.maven.mbean.CoreCRUDMBean;
import hu.ulyssys.java.course.maven.service.*;

import java.io.Serializable;
import java.util.List;

public abstract class OrderAwareCRUDMBean<T extends AbstractFurniture> extends CoreCRUDMBean<T> implements Serializable {

    private List<Courier> courierList;
    private List<AppUser> customerList;
    private List<Furniture> furnitureList;

    public OrderAwareCRUDMBean(CoreService<T> service, FurnitureService furnitureService, CourierService courierService, AppUserService customerService) {
        super(service);
        this.courierList = courierService.getAll();
        this.customerList = customerService.getAll();
        this.furnitureList = furnitureService.getAll();
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public void setCourierList(List<Courier> courierList) {
        this.courierList = courierList;
    }

    public List<AppUser> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<AppUser> customerList) {
        this.customerList = customerList;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }
}
