package hu.ulyssys.java.course.maven.converter;


import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.service.CourierService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AppConverter implements Converter {

    @Inject
    private CourierService courierService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }
        return courierService.getAll().stream().filter(courier ->
                (courier.getFirstName() + " " + courier.getLastName()).equals(s))
                .findFirst().orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Courier) {
            return ((Courier) o).getFullName();
        }
        if (o instanceof String) {
            return o.toString();
        }
        return null;
    }
}