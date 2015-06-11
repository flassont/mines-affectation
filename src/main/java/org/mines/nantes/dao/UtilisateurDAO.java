package org.mines.nantes.dao;

import org.mines.nantes.model.Affectation;
import org.mines.nantes.model.Module;
import org.mines.nantes.model.Utilisateur;

import javax.ejb.Stateless;
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
@Stateless
public class UtilisateurDAO {
    @Inject
    EntityManager entityManager;

    public Collection<Utilisateur> getAllUtilisateurs(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> criteria = cb.createQuery(Utilisateur.class);
        Root<Utilisateur> Utilisateur = criteria.from(Utilisateur.class);
        criteria.select(Utilisateur).distinct(true);
        return entityManager.createQuery(criteria).getResultList();

    }

    public void register(Utilisateur utilisateur){
        entityManager.persist(utilisateur);
    }

    public void update(Utilisateur utilisateur) throws Exception {
        entityManager.merge(utilisateur);
    }

    public void delete(String id) throws Exception {
        Utilisateur utilisateur = entityManager.find(Utilisateur.class,id);
        entityManager.remove(utilisateur);
    }
}
