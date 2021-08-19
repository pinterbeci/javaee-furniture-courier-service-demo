package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.rest.model.CourierRestModel;
import hu.ulyssys.java.course.maven.rest.model.FurnitureRestModel;

import javax.ws.rs.Path;

@Path("/furniture")
public class FurnitureRestService extends CoreRestService<Furniture, FurnitureRestModel> {

    @Override
    protected FurnitureRestModel initNewModel() {
        return new FurnitureRestModel();
    }

    @Override
    protected Furniture initNewEntity() {
        return new Furniture();
    }
}
