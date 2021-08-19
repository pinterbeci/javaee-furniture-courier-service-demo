package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.mbean.model.LoggedInUserModel;
import hu.ulyssys.java.course.maven.mbean.model.LoginModel;
import hu.ulyssys.java.course.maven.service.AppUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginRequestBean {

    @Inject
    private LoggedInUserBean bean;

    @Inject
    private AppUserService appUserService;

    private LoginModel loginModel = new LoginModel();

    public void doLogin() {
        try {
            AppUser appUser = appUserService.findByUsername(loginModel.getUsername());
            if (appUser == null) {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Nem létező felhasználó!"));

                throw new SecurityException("Nem létező felhasználó!");
            }

            String hassPassword = DigestUtils.sha512Hex(loginModel.getPassword());
            if (!hassPassword.equals(appUser.getPasswordHash())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Nem megfelelő jelszó!"));

                throw new SecurityException("Nem megfelelő jelszó!");
            }
            LoggedInUserModel loggedInUserModel = new LoggedInUserModel();
            loggedInUserModel.setUsername(appUser.getUsername());
            loggedInUserModel.setUserID(appUser.getId());
            loggedInUserModel.setRole(appUser.getRole());

            PrimeFaces.current().executeScript("PF('loginDialog').hide() ");
            bean.setLoggedInUserModel(loggedInUserModel);

        } catch (Exception e) {
            e.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Sikertelen bejelentkezés!"));
        }
    }


    public void doLogout() {
        bean.setLoggedInUserModel(null);
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }
}