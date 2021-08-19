package hu.ulyssys.java.course.maven.mbean.model;

import hu.ulyssys.java.course.maven.entity.AppUserRole;

public class LoggedInUserModel {


    private String username;
    private AppUserRole role;
    private Long userID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AppUserRole getRole() {
        return role;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}