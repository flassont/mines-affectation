package org.mines.nantes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Wish emited by a Utilisateur for a Module
 * Created by Florian on 29/05/2015.
 */
@Entity
public class Wish {

	@Id
	@GeneratedValue
	private int id;

	/** Year for which this Wish has been made */
	private int year;

	@ManyToOne
	private Utilisateur intervenant;

	@ManyToOne
	private Enseignement enseignement;

	/**
	 * Number of groups asked by the Utilisateur
	 */
	@Min(1)
	private int nbGroupes;

	private Boolean affecte;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Utilisateur getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(Utilisateur intervenant) {
		this.intervenant = intervenant;
	}

	public Enseignement getEnseignement() {
		return enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}

	public int getNbGroupes() {
		return nbGroupes;
	}

	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}

	public Boolean isAffecte() {
		return affecte;
	}

	public void setAffecte(Boolean affecte) {
		this.affecte = affecte;
	}
}
