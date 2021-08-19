package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CoreService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Named
@ViewScoped
public class AppUserCRUDMBean extends CoreCRUDMBean<AppUser> implements Serializable {


    @Inject
    public AppUserCRUDMBean(AppUserService userService) {
        super(userService);

   /*     if (!loggedInUserBean.isAdmin()) {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Nincs jogosultsága ehhez a művelethez!"));
            throw new SecurityException("Nincs jogosultsága ehhez a művelethez!");
        }*/

    }

    @Override
    protected String dialogName() {
        return "appUserDialog";
    }


  /*  @Override
    public void save() {
       try {
            if (getSelectedEntity().getId() == null) {
                getSelectedEntity().setPasswordHash(hashPassword(getSelectedEntity().getPasswordHash()));
            }
            super.save();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Hiba történt hash-elés közben!"));
            e.printStackTrace();
        }

        super.save();

    }*/

    private String hashPassword(String rowPassword) {
        return DigestUtils.sha512Hex(rowPassword);
    }


    @Override
    protected AppUser initNewEntity() {

        AppUser appUser = new AppUser();
        appUser.setPasswordHash(UUID.randomUUID().toString());
        return appUser;
    }
}
