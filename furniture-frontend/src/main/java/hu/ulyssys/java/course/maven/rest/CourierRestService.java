package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.mbean.LoggedInUserBean;
import hu.ulyssys.java.course.maven.rest.model.CourierRestModel;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CourierService;
import hu.ulyssys.java.course.maven.service.OrderService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.stream.Collectors;

@Path("/courier")
public class CourierRestService {
    @Inject
    private CourierService courierService;

    @Inject
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(courierService.getAll().stream().map(this::createModelFromEntity).
                collect(Collectors.toList())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCourier(@PathParam("id") Long id) {

        Courier entity = courierService.findById(id);

        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(courierService.getAll().stream().filter(e -> e.getId().equals(entity.getId())).
                collect(Collectors.toList())).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Courier entity = courierService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        orderService.deleteOrderByCourierID(entity.getId());
        courierService.deleteCourierFromOrder(entity.getId());
        courierService.remove(entity);
        return Response.ok().build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(CourierRestModel model) {
        Courier courier = new Courier();
        courier.setId(model.getId());
        courier.setFirstName(model.getFirstName());
        courier.setLastName(model.getLastName());
        courier.setPhoneNumber(model.getPhoneNumber());
        courier.setCreatedDate(new Date());
        courier.setCreatedUserID(model.getCreatedUserID());
        courierService.add(courier);
        return Response.ok(createModelFromEntity(courier)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(CourierRestModel model) {
        Courier courier = courierService.findById(model.getId());

        if (courier == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        courier.setModifiedDate(new Date());
        courier.setModifiedDate(model.getCreatedDate());
        courier.setModifierUserID(model.getModifierUserID());
        courier.setPhoneNumber(model.getPhoneNumber());
        courier.setLastName(model.getLastName());
        courier.setFirstName(model.getFirstName());

        courierService.update(courier);
        return Response.ok(createModelFromEntity(courier)).build();
    }

    private CourierRestModel createModelFromEntity(Courier courier) {
        CourierRestModel model = new CourierRestModel();

        model.setId(courier.getId());
        model.setFirstName(courier.getFirstName());
        model.setLastName(courier.getLastName());
        model.setPhoneNumber(courier.getPhoneNumber());
        model.setCreatedDate(courier.getCreatedDate());
        model.setCreatedUserID(courier.getCreatedUserID());

        return model;
    }
}