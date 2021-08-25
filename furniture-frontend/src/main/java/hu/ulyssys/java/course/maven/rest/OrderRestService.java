package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.Furniture;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.entity.PublicSpaceType;
import hu.ulyssys.java.course.maven.mbean.LoggedInUserBean;
import hu.ulyssys.java.course.maven.rest.model.OrderRestModel;
import hu.ulyssys.java.course.maven.service.AppUserService;
import hu.ulyssys.java.course.maven.service.CourierService;
import hu.ulyssys.java.course.maven.service.FurnitureService;
import hu.ulyssys.java.course.maven.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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

    @Inject
    private LoggedInUserBean loggedInUserBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(orderService.getAllOrderForUser().stream().map(this::createModelFromEntity).
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
        return Response.ok(orderService.getAllOrderForUser().stream().
                filter(e -> e.getId().equals(entity.getId())).
                collect(Collectors.toList())).build();
    }


    @GET
    @Path("/orderlist-by-owner-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("id") Long id) {
          /*  AppUser user = appUserService.findById(id);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }*/
        return Response.ok(orderService.getAllOrderForUser().stream().filter(e -> e.getCustomer().getId().equals(id)).
                collect(Collectors.toList())).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(OrderRestModel model) {
        Order order = new Order();
        order.setId(model.getId());
        order.setCreatedDate(model.getCreatedDate());
        order.setCreatedUserID(model.getCreatedUserID());
        order.setCustomer(appUserService.findById(model.getCustomerID()));
        order.setCourier(courierService.findById(model.getCourierID()));
        order.setDeliveryDate(model.getDeliveryDate());
        order.setNatureOfPublicSpace(model.getNatureOfPublicSpace());
        order.setPublicSpace(model.getPublicSpace());
        order.setSettlement(model.getSettlement());
        order.setAddressNumber(model.getAddressNumber());

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

        order.setCreatedDate(model.getCreatedDate());
        order.setCreatedUserID(model.getCreatedUserID());
        order.setCustomer(appUserService.findById(model.getCustomerID()));
        order.setCourier(courierService.findById(model.getCourierID()));
        order.setDeliveryDate(model.getDeliveryDate());
        order.setNatureOfPublicSpace(model.getNatureOfPublicSpace());
        order.setPublicSpace(model.getPublicSpace());
        order.setSettlement(model.getSettlement());
        order.setAddressNumber(model.getAddressNumber());

        order.setModifiedDate(new Date());
        order.setModifierUserID(10L);


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
    

    protected OrderRestModel createModelFromEntity(Order entity) {
        OrderRestModel model = initNewModel();
        model.setPublicSpace(entity.getPublicSpace());
        model.setSettlement(entity.getSettlement());
        model.setDeliveryDate(entity.getDeliveryDate());
        model.setNatureOfPublicSpace(entity.getNatureOfPublicSpace());
        model.setCreatedDate(entity.getCreatedDate());
        model.setId(entity.getId());
        model.setCreatedUserID(entity.getCreatedUserID());
        model.setCourierID(entity.getCourier().getId());
        model.setCustomerID(entity.getCustomer().getId());
        model.setModifiedDate(entity.getModifiedDate());
        model.setModifierUserID(entity.getModifierUserID());

        return model;
    }


    private Order initNewEntity() {

        try {
            Class<Order> type = (Class<Order>) (((ParameterizedType) ((Class) getClass().getGenericSuperclass()).getGenericSuperclass())).getActualTypeArguments()[1];
            return type.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private OrderRestModel initNewModel() {
        return new OrderRestModel();
    }


}
