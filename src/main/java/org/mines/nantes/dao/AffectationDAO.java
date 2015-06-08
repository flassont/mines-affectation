package org.mines.nantes.dao;

import org.mines.nantes.model.Affectation;
import org.mines.nantes.model.Uv;
import org.mines.nantes.model.Wish;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

/**
 * Created by mclaveau on 08/06/2015.
 */
@Stateless
public class AffectationDAO {

    @Inject
    EntityManager entityManager;

    public Collection<Affectation> getAllAffectation(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Affectation> criteria = cb.createQuery(Affectation.class);
        Root<Affectation> affectation = criteria.from(Affectation.class);
        criteria.select(affectation);
        return entityManager.createQuery(criteria).getResultList();
    }

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