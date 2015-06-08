package org.mines.nantes.dao;

import org.mines.nantes.model.Wish;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by mclaveau on 08/06/2015.
 */
@Stateless
public class WishDao {

    @Inject
    EntityManager entityManager;

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
