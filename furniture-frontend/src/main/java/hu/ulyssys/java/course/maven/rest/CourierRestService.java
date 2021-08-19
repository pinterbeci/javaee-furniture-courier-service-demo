package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.rest.model.CourierRestModel;

import javax.ws.rs.Path;

@Path("/courier")
public class CourierRestService extends CoreRestService<Courier, CourierRestModel> {

    @Override
    protected CourierRestModel initNewModel() {
        return new CourierRestModel();
    }

    @Override
    protected Courier initNewEntity() {
        return new Courier();
    }
}
