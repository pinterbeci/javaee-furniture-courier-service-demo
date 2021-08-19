package hu.ulyssys.java.course.maven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "furniture_courier_table")
public class Courier extends AbstractFurniture {

    @Column(name = "first_name", length = 500, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 500, nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    @Pattern(regexp = "\\+3630[0-9]{7}|\\+3620[0-9]{7}|\\+3670[0-9]{7}")
    private String phoneNumber;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public FurnitureType getFurnitureType() {
        return FurnitureType.COURIER;
    }
}
