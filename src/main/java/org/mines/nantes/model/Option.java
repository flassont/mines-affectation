package org.mines.nantes.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Florian on 28/05/2015.
 */
@Entity
public class Option extends Formation {

	@ManyToOne
	private Formation parent;
}
