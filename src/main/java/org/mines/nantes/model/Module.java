package org.mines.nantes.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * @summary Unit part of UV.
 * A Module is a set of lessons and classwork.
 * Created by Florian on 28/05/2015.
 */
@Entity
public class Module {

	@Id
	@GeneratedValue
	private int id;

	private String nom;

	/** Start date of the period during which the module takes place */
	private Date dateDebut;

	/** End date of the period during which the module takes place */
	private Date dateFin;

	/** Parent UV for the Module */
	@ManyToOne
	private Uv uv;

	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Enseignement> enseignements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebut() { return dateDebut;	}

	public void setDateDebut(Date dateDebut) {	this.dateDebut = dateDebut;	}

	public Date getDateFin() {	return dateFin;	}

	public void setDateFin(Date dateFin) {	this.dateFin = dateFin;	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Enseignement> getEnseignements() {
		return enseignements;
	}

	public void setEnseignements(Collection<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}
}
