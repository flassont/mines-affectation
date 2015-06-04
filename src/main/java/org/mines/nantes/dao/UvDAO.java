package org.mines.nantes.dao;

import org.mines.nantes.model.Uv;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

/**
 * Created by mclaveau on 01/06/2015.
 */
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

    /**
     * Get an UV by its id.
     * @param id ID of searched UV
     * @return The UV with this {@code id}, can be {@code null}
     */
    public Uv getUvById(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Uv> criteria = cb.createQuery(Uv.class);
        Root<Uv> uv = criteria.from(Uv.class);
        criteria.select(uv).where(cb.equal(uv.get("id"), id));

        // getSingleResult() throws NoResultException if no result
        List<Uv> result = entityManager.createQuery(criteria)
                .setMaxResults(1)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public void addUv(Uv uv){
        entityManager.persist(uv);
    }

}
