package org.mines.nantes.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Florian on 28/05/2015.
 */
@Entity
public class Formation {
	@Id
	@GeneratedValue
	private int id;

	@NotBlank
	private String name;

	/** List of specialization for this Formation */
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "parent"
	)
	private Collection<Option> options;

	@ManyToMany(
			cascade = {CascadeType.DETACH,
					CascadeType.PERSIST
			}
	)
	@JoinTable(
			joinColumns = @JoinColumn(name="formation_id"),
			inverseJoinColumns = @JoinColumn(name = "uv_id")
	)
	private Collection<Uv> uvs;
}
