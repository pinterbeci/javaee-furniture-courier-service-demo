package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.mbean.model.LoggedInUserModel;
import hu.ulyssys.java.course.maven.service.AppUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value = "registerUserBean")
@RequestScoped
public class RegistrateUserRequestBean {

    private String username;
    private String password;

    @Inject
    private LoggedInUserBean bean;

    @Inject
    private AppUserService appUserService;


    public RegistrateUserRequestBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void registrateUser() {

        try {
            if (username.equals("admin") && password.equals("admin") && appUserService.findByUsername("admin") == null) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setRole(AppUserRole.ADMIN);
                admin.setCreatedDate(new Date());
                admin.setPasswordHash(AppUserCRUDMBean.bytesToHex(DigestUtils.md5("admin")));
                appUserService.add(admin);

                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage("Sikeresen regisztrált, mint admin, megkapott minden jogosultságot!"));

                PrimeFaces.current().executeScript("PF('registerDialog').hide() ");


            } else if (appUserService.findByUsername(username) == null) {
                AppUser registeredUser = new AppUser();
                registeredUser.setUsername(username);
                registeredUser.setRole(AppUserRole.USER);
                registeredUser.setCreatedDate(new Date());
                registeredUser.setPasswordHash(AppUserCRUDMBean.bytesToHex(DigestUtils.md5(password)));
                appUserService.add(registeredUser);

                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage("Sikeres regisztráció!"));

            } else {
                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage("Ehhez a felhasználónévhez már tartozik fiók!"));
                throw new SecurityException("Ehhez a felhasználónévhez már tartozik fiók!");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Sikertelen regisztráció!"));
            throw new SecurityException("Sikertelen regisztráció!");
        }
    }
}

