package org.mines.nantes.rest;

import org.mines.nantes.dao.WishDAO;
import org.mines.nantes.model.Uv;
import org.mines.nantes.model.Wish;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

/**
 * Created by mclaveau on 08/06/2015.
 */
@Path("/wish")
public class WishService {

    /** Hold local URI information (provided by JAX-RS) */
    @Context
    UriInfo uriInfo;

    @Inject
    WishDAO wishDAO;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAllWish() {
        Collection<Wish> listWish = wishDAO.getAllWish();
        return Response.ok(listWish).build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.TEXT_PLAIN )
    public Response createWish(Wish wish) {
        if(wish == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            wishDAO.register(wish);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(wish.getId())).build();
            return Response.created(uri).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.WILDCARD)
    public Response deleteWish(@PathParam("id") int id) {
        if(id == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            wishDAO.delete(id);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(id)).build();
            return Response.created(uri).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
