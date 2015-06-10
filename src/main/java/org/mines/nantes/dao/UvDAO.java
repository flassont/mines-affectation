package org.mines.nantes.dao;


import org.mines.nantes.model.Module_;
import org.mines.nantes.model.Uv;
import org.mines.nantes.model.Uv_;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

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
        uv.fetch(Uv_.modules,JoinType.LEFT).fetch(Module_.enseignements,JoinType.LEFT);
        uv.fetch(Uv_.formations,JoinType.LEFT);
        criteria.select(uv).distinct(true);
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
        uv.fetch(Uv_.modules,JoinType.LEFT);
        uv.fetch(Uv_.formations,JoinType.LEFT);
        criteria.select(uv).distinct(true).where(cb.equal(uv.get("id"), id));

        // getSingleResult() throws NoResultException if no result
        List<Uv> result = entityManager.createQuery(criteria)
                .setMaxResults(1)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public void register(Uv uv){
        entityManager.persist(uv);
    }

    public void update(Uv uv) throws Exception {
        entityManager.merge(uv);
    }

    public void delete(String id) throws Exception {
        Uv uv = entityManager.find(Uv.class,id);
        entityManager.remove(uv);
    }
}
