package hu.ulyssys.java.course.maven.rest.model;

import hu.ulyssys.java.course.maven.entity.AppUserRole;

import java.util.Date;

public abstract class CoreRestModel {

    private Long id;

    private Date createdDate;

    private Date modifiedDate;

    private Long createdUserID;

    private Long modifierUserID;

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

    public Long getCreatedUserID() {
        return createdUserID;
    }

    public void setCreatedUserID(Long createdUserID) {
        this.createdUserID = createdUserID;
    }

    public Long getModifierUserID() {
        return modifierUserID;
    }

    public void setModifierUserID(Long modifierUserID) {
        this.modifierUserID = modifierUserID;
    }
}
