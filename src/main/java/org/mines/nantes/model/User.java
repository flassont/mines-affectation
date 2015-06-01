package org.mines.nantes.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * User of application.
 * It has personal informations (first name, last name),
 * email and password as credential
 * Created by Florian on 28/05/2015.
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Email
	@Column(unique = true)
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	/** List of Roles this User has been given access to */
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles;
}
