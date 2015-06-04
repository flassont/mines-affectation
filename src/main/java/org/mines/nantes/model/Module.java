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

	/** Hours of lessons */
	private double hCours;

	/** Hours of classwork */
	private double hTD;

	/** Number of groups following this Module */
	private int nbGroupes;

	/** Start date of the period during which the module takes place */
	private Date dateDebut;

	/** End date of the period during which the module takes place */
	private Date dateFin;

	/** Parent UV for the Module */
	@ManyToOne
	private Uv uv;

	/** Wishes emitted for this Module */
	@OneToMany(mappedBy = "module")
	private Collection<Wish> wishes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCours() {
		return hCours;
	}

	public void sethCours(double hCours) {
		this.hCours = hCours;
	}

	public double gethTD() {
		return hTD;
	}

	public void sethTD(double hTD) {
		this.hTD = hTD;
	}

	public int getNbGroupes() {
		return nbGroupes;
	}

	public void setNbGroupes(int nbGroupes) {
		this.nbGroupes = nbGroupes;
	}

	public Date getDateDebut() { return dateDebut;	}

	public void setDateDebut(Date dateDebut) {	this.dateDebut = dateDebut;	}

	public double gethCours() {	return hCours;	}

	public Date getDateFin() {	return dateFin;	}

	public void setDateFin(Date dateFin) {	this.dateFin = dateFin;	}

	public Uv getUv() {	return uv;	}

	public void setUv(Uv uv) {	this.uv = uv;	}

	public Collection<Wish> getWishes() {	return wishes;	}

	public void setWishes(Collection<Wish> wishes) {	this.wishes = wishes;	}
}
