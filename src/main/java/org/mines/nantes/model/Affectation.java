package org.mines.nantes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private Module moduleAffectation;

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

	public Module getModuleAffectation() {
		return moduleAffectation;
	}

	public void setModuleAffectation(Module moduleAffectation) {
		this.moduleAffectation = moduleAffectation;
	}
}
