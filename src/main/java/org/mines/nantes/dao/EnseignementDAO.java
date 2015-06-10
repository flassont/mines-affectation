package org.mines.nantes.dao;

import org.mines.nantes.model.Enseignement;
import org.mines.nantes.model.Enseignement_;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Florian on 10/06/2015.
 */
public class EnseignementDAO {

	@Inject
	EntityManager entityManager;

	public Enseignement getEnseignementById(int id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Enseignement> criteria = cb.createQuery(Enseignement.class);
		Root<Enseignement> enseignement = criteria.from(Enseignement.class);
		enseignement.fetch(Enseignement_.wishs, JoinType.LEFT);
		criteria.select(enseignement).distinct(true).where(cb.equal(enseignement.get("id"), id));

		// getSingleResult() throws NoResultException if no result
		List<Enseignement> result = entityManager.createQuery(criteria)
				.setMaxResults(1)
				.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}
}
