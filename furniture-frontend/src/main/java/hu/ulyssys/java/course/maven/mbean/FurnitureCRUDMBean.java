package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.service.CoreService;
import hu.ulyssys.java.course.maven.service.FurnitureService;
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
    public void save() {
        try {
            if (getSelectedEntity().getId() == null) {
                getSelectedEntity().setCreatedDate(new Date());
                getSelectedEntity().setCreatedUser(loggedInUserBean.getLoggedInUserModel().getRole());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres mentés"));

            } else {

                getSelectedEntity().setModifiedDate(new Date());
                getSelectedEntity().setCreatedUser(loggedInUserBean.getLoggedInUserModel().getRole());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módosítás"));
            }

            super.save();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Hiba történt hash-elés közben!"));
            e.printStackTrace();
        }

        super.save();

    }


    @Override
    protected String dialogName() {
        return "furnitureDialog";
    }

    @Override
    protected Furniture initNewEntity() {
        return new Furniture();
    }
}
