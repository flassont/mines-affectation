package org.mines.nantes.rest;

import org.mines.nantes.dao.WishDAO;
import org.mines.nantes.model.Wish;

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
}
