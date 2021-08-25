package hu.ulyssys.java.course.maven.mbean;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CoreService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Id;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.UUID;

@Named
@ViewScoped
public class AppUserCRUDMBean extends CoreCRUDMBean<AppUser> implements Serializable {


    @Inject
    public AppUserCRUDMBean(AppUserService userService, LoggedInUserBean loggedInUserBean) {
        super(userService);
        if (!loggedInUserBean.isAdmin()) {
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Nincs jogosultsága ehhez a művelethez!"));
            throw new SecurityException("Nincs jogosultsága ehhez a művelethez!");
        }

    }

    @Override
    protected String dialogName() {
        return "appUserDialog";
    }


    @Override
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
    }

    @Override
    protected AppUser initNewEntity() {
        return new AppUser();
    }

    private String hashPassword(String rowPassword) {
        byte[] hash = DigestUtils.md5(rowPassword);
        return bytesToHex(hash);
    }

    protected static String bytesToHex(byte[] hash) {

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
