package hu.ulyssys.java.course.maven.rest.model;

import hu.ulyssys.java.course.maven.entity.AppUserRole;

import java.util.Date;

public abstract class CoreRestModel {

    private Long id;

    private Date createdDate;

    private Date modifiedDate;

    private AppUserRole createdUser;

    private AppUserRole modifierUser;


    public CoreRestModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public AppUserRole getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(AppUserRole createdUser) {
        this.createdUser = createdUser;
    }

    public AppUserRole getModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(AppUserRole modifierUser) {
        this.modifierUser = modifierUser;
    }
}
