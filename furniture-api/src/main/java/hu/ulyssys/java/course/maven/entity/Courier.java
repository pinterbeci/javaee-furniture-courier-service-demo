package hu.ulyssys.java.course.maven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Objects;


@Entity
@Table(name = "furniture_courier_table")
public class Courier extends AbstractFurniture {

    private static final String PATTERN_MESSAGE = "Meg kell feleljen egy magyar telefonszám szabványnak" +
            "\n pld.: +36 20/30/70 XX XX XXX!";
    @Column(name = "first_name", length = 500, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 500, nullable = false)
    private String lastName;


    @Column(name = "phone_number", nullable = false)
    @Pattern(regexp = "\\+3630[0-9]{7}|\\+3620[0-9]{7}|\\+3670[0-9]{7}", message = PATTERN_MESSAGE)
    private String phoneNumber;

    private String fullName;

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


    public String getFullName() {
        return fullName;
    }

    public void setFullName() {
        this.fullName = getFirstName() + " " + getLastName();
    }

    @Override
    public FurnitureType getFurnitureType() {
        return FurnitureType.COURIER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(firstName, courier.firstName) && Objects.equals(lastName, courier.lastName) && Objects.equals(phoneNumber, courier.phoneNumber) && Objects.equals(fullName, courier.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, fullName);
    }
}
