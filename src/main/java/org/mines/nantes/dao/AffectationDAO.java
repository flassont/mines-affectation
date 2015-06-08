package org.mines.nantes.dao;

import org.mines.nantes.model.Affectation;
import org.mines.nantes.model.Wish;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by mclaveau on 08/06/2015.
 */
@Stateless
public class AffectationDAO {

    @Inject
    EntityManager entityManager;

    public void register(Affectation affectation){
        entityManager.persist(affectation);
    }

    public void update(Affectation affectation) throws Exception {
        entityManager.merge(affectation);
    }

    public void delete(String id) throws Exception {
        Affectation affectation = entityManager.find(Affectation.class,id);
        entityManager.remove(affectation);
    }
}