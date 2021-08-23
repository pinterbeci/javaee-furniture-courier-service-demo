package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.AppUserRole;
import hu.ulyssys.java.course.maven.entity.Courier;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.rest.model.CourierRestModel;
import hu.ulyssys.java.course.maven.rest.model.FurnitureRestModel;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CourierService;
import hu.ulyssys.java.course.maven.service.FurnitureService;
import hu.ulyssys.java.course.maven.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.stream.Collectors;

@Path("/furniture")
public class FurnitureRestService {
    @Inject
    private FurnitureService furnitureService;

    @Inject
    private OrderService orderService;

    @Inject
    private AppUserService appUserService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(furnitureService.getAll().stream().map(this::createModelFromEntity).
                collect(Collectors.toList())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCourier(@PathParam("id") Long id) {
        Furniture entity = furnitureService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(furnitureService.getAll().stream().filter(e -> e.getId().equals(entity.getId())).
                collect(Collectors.toList())).build();
    }

    //todo
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Furniture entity = furnitureService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        //todo furniture listából törlés
        // courierService.removeConstraintItem(id);
        furnitureService.remove(entity);
        return Response.ok().build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(FurnitureRestModel model) {
        Furniture furniture = new Furniture();
        furniture.setId(model.getId());
        furniture.setFurnitureName(model.getFurnitureName());
        furniture.setPrice(model.getPrice());
        furniture.setDescription(model.getDescription());

        furniture.setCreatedDate(model.getCreatedDate());
        //todo ha lesz bejelentkezés, akkor ide USERt kell rendelni!!!
        furniture.setCreatedUser(AppUserRole.USER);
        furnitureService.add(furniture);
        return Response.ok(createModelFromEntity(furniture)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(FurnitureRestModel model) {
        Furniture furniture = furnitureService.findById(model.getId());

        if (furniture == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        furniture.setModifiedDate(new Date());
        furniture.setModifiedDate(model.getCreatedDate());
        furniture.setModifierUser(AppUserRole.ADMIN);
        furniture.setFurnitureName(model.getFurnitureName());
        furniture.setPrice(model.getPrice());
        furniture.setDescription(model.getDescription());

        furnitureService.update(furniture);
        return Response.ok(createModelFromEntity(furniture)).build();
    }

    private FurnitureRestModel createModelFromEntity(Furniture furniture) {
        FurnitureRestModel model = new FurnitureRestModel();

        model.setId(furniture.getId());
        furniture.setFurnitureName(model.getFurnitureName());
        furniture.setPrice(model.getPrice());
        furniture.setDescription(model.getDescription());

        model.setCreatedDate(furniture.getCreatedDate());
        //todo ha lesz bejelentkezés, akkor ide USERt kell rendelni!!!
        model.setCreatedUserID(null);

        return model;
    }
}
