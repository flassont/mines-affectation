package org.mines.nantes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Florian on 28/05/2015.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	private Set<Option> options;

	@ManyToMany(
			cascade = {CascadeType.DETACH,
					CascadeType.PERSIST
			}
	)
	@JoinTable(
			joinColumns = @JoinColumn(name="formation_id"),
			inverseJoinColumns = @JoinColumn(name = "uv_id")
	)
	private Set<Uv> uvs;

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

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	public Set<Uv> getUvs() {
		return uvs;
	}

	public void setUvs(Set<Uv> uvs) {
		this.uvs = uvs;
	}
}
