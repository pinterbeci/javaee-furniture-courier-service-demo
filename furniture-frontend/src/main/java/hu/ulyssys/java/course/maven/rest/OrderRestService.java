package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.entity.PublicSpaceType;
import hu.ulyssys.java.course.maven.rest.model.OrderRestModel;
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

@Path("/order")
public class OrderRestService {
    @Inject
    private OrderService orderService;

    @Inject
    private CourierService courierService;

    @Inject
    private FurnitureService furnitureService;

    @Inject
    private AppUserService appUserService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(orderService.getAll().stream().map(this::createModelFromEntity).
                collect(Collectors.toList())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findVehicleById(@PathParam("id") Long id) {

        Order entity = orderService.findById(id);

        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(orderService.getAll().stream().
                filter(e -> e.getId().equals(entity.getId())).
                collect(Collectors.toList())).build();
    }


    @GET
    @Path("/orderlist-by-owner-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("id") Long id) {
        AppUser user = appUserService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(orderService.getAll().stream().filter(e -> e.getCustomer().equals(user)).
                collect(Collectors.toList())).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(OrderRestModel model) {
        Order order = new Order();
        order.setId(model.getId());
        order.setDeliveryDate(model.getDeliveryDate());
        order.setPublicSpace(model.getPublicSpace());
        order.setAddressNumber(model.getAddressNumber());
        order.setSettlement(model.getSettlement());
        //todo
        order.setNatureOfPublicSpace(model.getNatureOfPublicSpace());
        order.setModifiedDate(null);
        order.setCustomer(appUserService.findById(model.getCustomerID()));
        order.setCreatedDate(new Date());
        //todo
        order.setModifierUser(null);
        order.setCourier(courierService.findById(model.getId()));
        order.setFurnitureList(furnitureService.findByOrderID(model.getId()));
        order.setDeliveryDate(model.getDeliveryDate());

        orderService.add(order);
        return Response.ok(createModelFromEntity(order)).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(OrderRestModel model) {
        Order order = orderService.findById(model.getId());
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        order.setDeliveryDate(model.getDeliveryDate());
        order.setPublicSpace(model.getPublicSpace());
        order.setAddressNumber(model.getAddressNumber());
        order.setSettlement(model.getSettlement());
        order.setNatureOfPublicSpace(model.getNatureOfPublicSpace());
        order.setModifiedDate(new Date());
        order.setCustomer(appUserService.findById(model.getCustomerID()));
        order.setCreatedDate(model.getCreatedDate());
        //todo
        order.setModifierUser(null);
        order.setCourier(courierService.findById(model.getId()));
        order.setFurnitureList(furnitureService.findByOrderID(model.getId()));
        order.setDeliveryDate(model.getDeliveryDate());

        orderService.update(order);
        return Response.ok(createModelFromEntity(order)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        orderService.remove(order);
        return Response.ok().build();
    }


    private OrderRestModel createModelFromEntity(Order order) {
        OrderRestModel model = new OrderRestModel();
        model.setCustomerID(order.getCustomer().getId());
        model.setAddressNumber(order.getAddressNumber());
        model.setDeliveryDate(order.getDeliveryDate());
        model.setSettlement(order.getSettlement());
        model.setNatureOfPublicSpace(order.getNatureOfPublicSpace());
        model.setPublicSpace(order.getPublicSpace());
        //todo created user
        model.setCourierID(courierService.findById(model.getId()).getId());
        model.setFurnitureIDList(
                furnitureService.getAll().stream().map(Furniture::getId)
                        .collect(Collectors.toList()));
        return model;
    }
}
