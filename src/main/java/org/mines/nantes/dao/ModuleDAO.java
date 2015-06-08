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

/**
 * Created by Alex Gourbiliere on 08/06/2015.
 */
public class ModuleDAO {

    @Inject
    EntityManager entityManager;

    public Collection<Utilisateur> getUtilisateursByModule(int idModule){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        /* Subquery to get the right module */
        CriteriaQuery<Module> criteriaModule = cb.createQuery(Module.class);
        Root<Module> rootModule = criteriaModule.from(Module.class);
        Predicate conditionId = cb.equal(rootModule.get("id"), idModule);
        criteriaModule.where(conditionId);
        Module module = entityManager.createQuery(criteriaModule).getResultList().get(0);

        /* Query to get affectations with the module above */
        CriteriaQuery<Affectation> criteriaAffect = cb.createQuery(Affectation.class);
        Root<Affectation> rootAffect = criteriaAffect.from(Affectation.class);
        Predicate conditionModule = cb.equal(rootAffect.get("moduleAffectation"), module);
        criteriaAffect.where(conditionModule);

        Collection<Affectation> listAffectations = entityManager.createQuery(criteriaAffect).getResultList();

        Collection<Utilisateur> listUtilisateursModule = new ArrayList<Utilisateur>();
        for (Affectation a : listAffectations) {
            listUtilisateursModule.add(a.getIntervenant());
        }

        return listUtilisateursModule;
    }
}
