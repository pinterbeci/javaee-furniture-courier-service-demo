package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AbstractFurniture;
import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.FurnitureService;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractOrderCRUDMBean<T extends AbstractFurniture> extends CoreCRUDMBean<T> implements Serializable {

    private List<AppUser> customerList;
    private List<Furniture> furnitureList;

    public AbstractOrderCRUDMBean(CoreService<T> service, AppUserService customerService, FurnitureService furnitureService) {
        super(service);
        customerList = customerService.getAll();
        furnitureList = furnitureService.getAll();
    }
}
