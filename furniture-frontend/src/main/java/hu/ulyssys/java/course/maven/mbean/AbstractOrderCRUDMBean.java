package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.*;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.CourierService;
import hu.ulyssys.java.course.maven.service.FurnitureService;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractOrderCRUDMBean<T extends AbstractFurniture> extends CoreCRUDMBean<T> implements Serializable {

    private List<AppUser> customerList;
    private List<Furniture> furnitureList;
    private List<Courier> courierList;

    public AbstractOrderCRUDMBean(CoreService<T> service, AppUserService customerService,
                                  CourierService courierService, FurnitureService furnitureService) {
        super(service);
        customerList = customerService.getAll();
        furnitureList = furnitureService.getAll();
        courierList = courierService.getAll();
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

    public List<Courier> getCourierList() {
        return courierList;
    }

    public void setCourierList(List<Courier> courierList) {
        this.courierList = courierList;
    }
}
