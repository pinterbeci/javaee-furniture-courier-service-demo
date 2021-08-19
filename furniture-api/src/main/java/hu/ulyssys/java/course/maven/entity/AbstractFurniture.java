package hu.ulyssys.java.course.maven.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractFurniture extends AbstractEntity implements FurnitureTypeAware {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "created_user", nullable = false)
    private AppUserRole createdUser;

    @Column(name = "modifier_user", nullable = false)
    private AppUserRole modifierUser;

    public AbstractFurniture() {
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
