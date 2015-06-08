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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Option> getOptions() {
		return options;
	}

	public void setOptions(Collection<Option> options) {
		this.options = options;
	}

	public Collection<Uv> getUvs() {
		return uvs;
	}

	public void setUvs(Collection<Uv> uvs) {
		this.uvs = uvs;
	}
}
