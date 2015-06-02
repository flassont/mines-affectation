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
public class Wish {

	@Id
	@GeneratedValue
	private int id;

	/** Year for which this Wish has been made */
	private int year;

	@ManyToOne
	private Intervenant intervenant;

	@ManyToOne
	private Module module;
}
