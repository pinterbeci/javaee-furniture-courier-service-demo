package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.mbean.model.LoggedInUserModel;
import hu.ulyssys.java.course.maven.mbean.model.LoginModel;
import hu.ulyssys.java.course.maven.service.AppUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

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

            String hashPassword2 = AppUserCRUDMBean.bytesToHex(DigestUtils.md5(loginModel.getPassword()));
            if (!hashPassword2.equals(appUser.getPasswordHash())) {
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

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Sikeres kilépés!"));
    }


    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }


}