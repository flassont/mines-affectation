package org.mines.nantes.rest;

import org.mines.nantes.dao.AuthentificationDAO;
import org.mines.nantes.model.Utilisateur;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Alex Gourbiliere on 09/06/2015.
 */
@Path("/auth")
public class AuthentificationService {

    @Inject
    private Event<Utilisateur> memberEventSrc;

    @Inject
    private AuthentificationDAO authentificationDAO;

    private final Map<String, String> authorizationTokensStorage = new HashMap();

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public String login(@QueryParam("login") String email, @QueryParam("password") String password) {

        Utilisateur intervenant = authentificationDAO.verifAuthentification(email, password);

        if (intervenant != null) {
            // On génère un token
            String authToken = UUID.randomUUID().toString();
            authorizationTokensStorage.put(authToken, email);
            return authToken;
        } else
            return "Erreur lors de la saisie du login ou du mot de passe.";
    }

    public boolean isAuthTokenValid(String authToken) {
        return authorizationTokensStorage.containsKey(authToken);
    }

    public void logout(String email, String authToken) {
        if (authorizationTokensStorage.containsKey(authToken)) {
            String email2 = authorizationTokensStorage.get(authToken);
            if (email.equals(email2)) {
                authorizationTokensStorage.remove( authToken );
            }
        }
        return;
    }
}
