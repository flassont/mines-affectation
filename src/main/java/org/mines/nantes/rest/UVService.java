package org.mines.nantes.rest;

import org.mines.nantes.dao.UvDAO;
import org.mines.nantes.model.Uv;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by mclaveau on 01/06/2015.
 */
@Path("/uv")
public class UVService {

    @Inject
    UvDAO uvDAO;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Collection<Uv> getAllUV() {
        Collection<Uv> listUv = uvDAO.getAllUV();
        return listUv;
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

}
