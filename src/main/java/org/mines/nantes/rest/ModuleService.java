package org.mines.nantes.rest;

import org.mines.nantes.dao.ModuleDAO;
import org.mines.nantes.model.Utilisateur;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * Created by Alex Gourbiliere on 08/06/2015.
 */

@Path("/module")
public class ModuleService {

    /** Hold local URI information (provided by JAX-RS) */
    @Context
    UriInfo uriInfo;

    @Inject
    ModuleDAO moduleDAO;

    @GET
    @Path( "{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Collection<Utilisateur> getIntervenantsModule(@PathParam("id") int idModule) {
        Collection<Utilisateur> listUtilisateurs = moduleDAO.getUtilisateursByModule(idModule);

        return listUtilisateurs;
    }
}
