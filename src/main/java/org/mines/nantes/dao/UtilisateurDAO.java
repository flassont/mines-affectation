package org.mines.nantes.dao;

import org.mines.nantes.model.Affectation;
import org.mines.nantes.model.Module;
import org.mines.nantes.model.Utilisateur;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alex Gourbiliere on 04/06/2015.
 */
public class UtilisateurDAO {
    @Inject
    EntityManager entityManager;

    public Collection<Utilisateur> getAllUtilisateurs(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> criteria = cb.createQuery(Utilisateur.class);
        Root<Utilisateur> Utilisateur = criteria.from(Utilisateur.class);
        criteria.select(Utilisateur);
        return entityManager.createQuery(criteria).getResultList();

    }

    public void addUtilisateur(Utilisateur utilisateur){
        entityManager.persist(utilisateur);
    }
}
