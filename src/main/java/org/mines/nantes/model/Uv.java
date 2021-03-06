package org.mines.nantes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

/**
 * Unité de Valeur.
 * An UV is composed of some Module and is linked by at least one Formation
 * Created by Florian on 28/05/2015.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Uv {
	@Id
	@GeneratedValue
	private int id;

	private String nom;

	@OneToOne
	private Utilisateur responsableUV;

	/** List of Module composing this Uv */
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "uv"
	)
	private Set<Module> modules;

	/** List of Formation following this Uv */
	@ManyToMany(
			mappedBy = "uvs")
	private Set<Formation> formations;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getResponsableUV() {
		return responsableUV;
	}

	public void setResponsableUV(Utilisateur responsableUV) {
		this.responsableUV = responsableUV;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Set<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}
}
