package org.mines.nantes.model;

import javax.persistence.*;
import java.util.Collection;

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
}
