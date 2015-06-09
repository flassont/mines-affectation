package org.mines.nantes.dao;

import org.mines.nantes.model.Utilisateur;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

/**
 * Created by gourbi on 09/06/2015.
 */
public class AuthentificationDAO {

    @Inject
    EntityManager entityManager;

    public Utilisateur verifAuthentification(String login, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> criteria = cb.createQuery(Utilisateur.class);
        Root<Utilisateur> member = criteria.from(Utilisateur.class);
        criteria.select(member).where(cb.equal(member.get("email"), login));

        List<Utilisateur> result = entityManager.createQuery(criteria)
                .setMaxResults(1)
                .getResultList();

        if (result.size() !=0 ) {
            Utilisateur utilisateur = result.get(0);

            if(utilisateur.getPassword().equals(password))
                return utilisateur;
            else
                return null;
        } else
            return null;
    }
}
