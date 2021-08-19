package hu.ulyssys.java.course.maven.rest;

import hu.ulyssys.java.course.maven.entity.AbstractFurniture;
import hu.ulyssys.java.course.maven.entity.AppUser;
import hu.ulyssys.java.course.maven.entity.Order;
import hu.ulyssys.java.course.maven.rest.model.CoreRestModel;
import hu.ulyssys.java.course.maven.service.CoreService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.stream.Collectors;

public abstract class CoreRestService<T extends AbstractFurniture, M extends CoreRestModel> {

    @Inject
    protected CoreService<T> service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(service.getAll().stream().map(this::createModelFromEntity).
                collect(Collectors.toList())).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid M model) {

        T entity = initNewEntity();

        populateEntityFromModel(entity, model);
        service.add(entity);
        return Response.ok(createModelFromEntity(entity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        T entity = service.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        service.remove(entity);
        return Response.ok().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findVehicleById(@PathParam("id") Long id) {

        T entity = service.findById(id);

        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(service.getAll().stream().
                filter(e -> e.getId().equals(entity.getId())).
                collect(Collectors.toList())).build();
    }


    protected void populateEntityFromModel(T entity, M model) {
        entity.setCreatedDate(model.getCreatedDate());
        entity.setCreatedUser(model.getCreatedUser());
        entity.setCreatedUser(model.getCreatedUser());
        entity.setModifierUser(model.getModifierUser());
    }


    protected M createModelFromEntity(T entity) {
        M model = initNewModel();
        model.setCreatedDate(entity.getCreatedDate());
        model.setId(entity.getId());
        model.setModifiedDate(entity.getModifiedDate());
        model.setCreatedUser(entity.getCreatedUser());
        model.setModifierUser(entity.getModifierUser());

        return model;
    }

    protected T initNewEntity() {

        try {
            Class<T> type = (Class<T>) (((ParameterizedType)
                    ((Class) getClass().getGenericSuperclass()).getGenericSuperclass()))
                    .getActualTypeArguments()[1];
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

    protected abstract M initNewModel();

}
