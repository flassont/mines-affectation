package org.mines.nantes.dao;

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
public class WishDAO {

    @Inject
    EntityManager entityManager;

    public Collection<Wish> getAllWish(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Wish> criteria = cb.createQuery(Wish.class);
        Root<Wish> wish = criteria.from(Wish.class);
        criteria.select(wish).distinct(true);
        return entityManager.createQuery(criteria).getResultList();
    }

    public void register(Wish wish){
        entityManager.persist(wish);
    }


    public void update(Wish wish) throws Exception {
        entityManager.merge(wish);
    }

    public void delete(String id) throws Exception {
        Wish wish = entityManager.find(Wish.class,id);
        entityManager.remove(wish);
    }
}
