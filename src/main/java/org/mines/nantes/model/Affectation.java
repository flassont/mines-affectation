package org.mines.nantes.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Wish emited by a Utilisateur for a Module
 * Created by Florian on 29/05/2015.
 */
@Entity
public class Affectation {

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

	public int getNbGroupes() {
		return nbGroupes;
	}

	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}

	public Enseignement getEnseignement() {
		return enseignement;
	}

	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}
}
