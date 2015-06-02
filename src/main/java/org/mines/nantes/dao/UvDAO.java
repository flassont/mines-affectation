package org.mines.nantes.dao;

import org.mines.nantes.model.Uv;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

/**
 * Created by mclaveau on 01/06/2015.
 */
@Stateless
public class UvDAO {

    @Inject
    EntityManager entityManager;

    public Collection<Uv> getAllUV(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Uv> criteria = cb.createQuery(Uv.class);
        Root<Uv> uv = criteria.from(Uv.class);
        criteria.select(uv);
        return entityManager.createQuery(criteria).getResultList();

    }

}
