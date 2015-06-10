package org.mines.nantes.rest;

import org.mines.nantes.dao.AffectationDAO;
import org.mines.nantes.model.Affectation;

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
@Path("/affectation")
public class AffectationService {

    /** Hold local URI information (provided by JAX-RS) */
    @Context
    UriInfo uriInfo;

    @Inject
    AffectationDAO affectationDAO;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAllAffectation() {
        Collection<Affectation> listAffectation = affectationDAO.getAllAffectation();
        return Response.ok(listAffectation).build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.TEXT_PLAIN )
    public Response createAffectation(Affectation affectation) {
        if(affectation == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            affectationDAO.register(affectation);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(affectation.getId())).build();
            return Response.created(uri).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response updateUv(Affectation affectation) {
        if(affectation == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            affectationDAO.update(affectation);
            return Response.ok(affectation).build();
        } catch(ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
