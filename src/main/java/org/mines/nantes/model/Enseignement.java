package org.mines.nantes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Enseignement {
	@GeneratedValue
	@Id
	private int id;

	private int nbHeures;

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

	@ManyToOne
	private Module module;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbHeures() {
		return nbHeures;
	}

	public void setNbHeures(int nbHeures) {
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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
}
