package hu.ulyssys.java.course.maven.mbean;


import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.mbean.model.LoggedInUserModel;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoggedInUserBean implements Serializable {


    private LoggedInUserModel loggedInUserModel;


    public LoggedInUserModel getLoggedInUserModel() {
        return loggedInUserModel;
    }

    public void setLoggedInUserModel(LoggedInUserModel loggedInUserModel) {
        this.loggedInUserModel = loggedInUserModel;
    }

    public boolean isLoggedIn() {
        return loggedInUserModel != null;
    }

    public boolean isAdmin() {

        return isLoggedIn() && loggedInUserModel.getRole().equals(AppUserRole.ADMIN);
    }
}