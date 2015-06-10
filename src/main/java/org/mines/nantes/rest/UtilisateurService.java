package org.mines.nantes.rest;

import org.mines.nantes.dao.UtilisateurDAO;
import org.mines.nantes.model.Utilisateur;

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
 * Created by Alex Gourbiliere on 04/06/2015.
 */

@Path("/user")
public class UtilisateurService {

    @Context
    UriInfo uriInfo;

    @Inject
    UtilisateurDAO utilisateurDAO;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Collection<Utilisateur> getUtilisateurs() {
        Collection<Utilisateur> listUtilisateurs = utilisateurDAO.getAllUtilisateurs();
        return listUtilisateurs;
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.TEXT_PLAIN )
    public Response createUtilisateur(Utilisateur utilisateur) {
        if(utilisateur == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            utilisateurDAO.register(utilisateur);
            URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(utilisateur.getId())).build();
            return Response.created(uri).build();
        } catch (ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response updateUv(Utilisateur utilisateur) {
        if(utilisateur == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            utilisateurDAO.update(utilisateur);
            return Response.ok(utilisateur).build();
        } catch(ConstraintViolationException cve) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
