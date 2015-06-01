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

	/** List of Module composing this Uv */
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "uv"
	)
	private Collection<Module> modules;

	/** List of Formation following this Uv */
	@ManyToMany(mappedBy = "uvs")
	private Collection<Formation> formations;
}
