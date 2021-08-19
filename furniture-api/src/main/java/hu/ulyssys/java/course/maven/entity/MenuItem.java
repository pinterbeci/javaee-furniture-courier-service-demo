package hu.ulyssys.java.course.maven.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "furniture_menu_item")
@Entity
public class MenuItem extends AbstractEntity {

    private String url;
    private String label;
    private Boolean adminFunction;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(Boolean adminFunction) {
        this.adminFunction = adminFunction;
    }
}
