package org.mines.nantes.rest;

import org.mines.nantes.dao.AffectationDAO;
import org.mines.nantes.model.Affectation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
}
