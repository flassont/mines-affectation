package org.mines.nantes.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Unité de Valeur.
 * An UV is composed of some Module and is linked by at least one Formation
 * Created by Florian on 28/05/2015.
 */
@Entity
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
			mappedBy = "uv",
			fetch = FetchType.EAGER
	)
	private Collection<Module> modules;

	/** List of Formation following this Uv */
	@ManyToMany(
			mappedBy = "uvs",
			fetch = FetchType.EAGER)
	private Collection<Formation> formations;

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

	public void setResponsableUV(Utilisateur responsableUV) {
		this.responsableUV = responsableUV;
	}

	public Collection<Module> getModules() {
		return modules;
	}

	public void setModules(Collection<Module> modules) {
		this.modules = modules;
	}

	public Collection<Formation> getFormations() {
		return formations;
	}

	public void setFormations(Collection<Formation> formations) {
		this.formations = formations;
	}
}
