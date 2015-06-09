package org.mines.nantes.rest;

import org.mines.nantes.dao.UvDAO;
import org.mines.nantes.model.Uv;

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
 * Created by mclaveau on 01/06/2015.
 */
@Path("/uv")
public class UVService {

    /** Hold local URI information (provided by JAX-RS) */
    @Context
    UriInfo uriInfo;

    @Inject
    UvDAO uvDAO;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAllUV() {
        Collection<Uv> listUv = uvDAO.getAllUV();
        return Response.ok(listUv).build();
    }

    @GET
    @Path("{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response getUv(@PathParam("id") int id) {
        Uv uv = uvDAO.getUvById(id);
        if(uv == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(uv).build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.TEXT_PLAIN )
    public Response createUV(Uv uv) {
        if(uv == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            uvDAO.register(uv);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(uv.getId())).build();
            return Response.created(uri).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response updateUv(Uv uv) {
        if(uv == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            uvDAO.update(uv);
            return Response.ok(uv).build();
        } catch(ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
