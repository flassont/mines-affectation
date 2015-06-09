package org.mines.nantes.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.Period;
import java.util.Collection;
import java.util.Set;

/**
 * Part of a Module
 * Created by Florian on 08/06/2015.
 */
@Entity
public class Enseignement {
	@GeneratedValue
	@Id
	private int id;

	private Period nbHeures;

	@Min(1)
	private int nbGroupes;

	/**
	 * Type (cours,...)
	 * @see FormePedagogique
	 */
	@Enumerated(EnumType.STRING)
	private FormePedagogique forme;

	@OneToMany(mappedBy = "enseignement")
	private Set<Wish> wishs;

	@OneToMany(mappedBy = "enseignement")
	private Set<Affectation> affectations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Period getNbHeures() {
		return nbHeures;
	}

	public void setNbHeures(Period nbHeures) {
		this.nbHeures = nbHeures;
	}

	public int getNbGroupes() {
		return nbGroupes;
	}

	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}

	public FormePedagogique getForme() {
		return forme;
	}

	public void setForme(FormePedagogique forme) {
		this.forme = forme;
	}

	public Set<Wish> getWishs() {
		return wishs;
	}

	public void setWishs(Set<Wish> wishs) {
		this.wishs = wishs;
	}

	public Collection<Affectation> getAffectations() {
		return affectations;
	}

	public void setAffectations(Set<Affectation> affectations) {
		this.affectations = affectations;
	}
}
