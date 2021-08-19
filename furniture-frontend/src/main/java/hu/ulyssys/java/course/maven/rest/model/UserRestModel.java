package hu.ulyssys.java.course.maven.rest.model;

import java.util.Date;

public class UserRestModel {

    private  Long id;

    private String username;

    private Date registrationDate;

    public UserRestModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
