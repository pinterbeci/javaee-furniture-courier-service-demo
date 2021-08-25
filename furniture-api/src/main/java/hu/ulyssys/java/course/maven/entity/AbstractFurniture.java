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
    private Long createdUserID;

    @Column(name = "modifier_user")
    private Long modifierUserID;

    public AbstractFurniture() {
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
