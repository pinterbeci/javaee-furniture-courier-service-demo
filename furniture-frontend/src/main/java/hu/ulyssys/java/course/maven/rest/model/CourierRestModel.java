package hu.ulyssys.java.course.maven.rest.model;

import java.util.Date;

public class CourierRestModel extends CoreRestModel {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public CourierRestModel() {
    }


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
}
