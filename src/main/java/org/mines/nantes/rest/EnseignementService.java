package org.mines.nantes.rest;

import org.mines.nantes.dao.EnseignementDAO;
import org.mines.nantes.model.Enseignement;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Florian on 10/06/2015.
 */
@Path("enseignement")
public class EnseignementService {

	@Inject
	EnseignementDAO enseignementDAO;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Enseignement getEnseignement(@PathParam("id") int id) {
		return enseignementDAO.getEnseignementById(id);
	}
}
