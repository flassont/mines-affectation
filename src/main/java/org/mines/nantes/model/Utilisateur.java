package org.mines.nantes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

/**
 * Utilisateur of application.
 * It has personal informations (first name, last name),
 * email and password as credential
 * Created by Florian on 28/05/2015.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	private boolean admin;

	@Enumerated(EnumType.STRING)
	private UtilisateurType role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public UtilisateurType getRole() {
		return role;
	}

	public void setRole(UtilisateurType role) {
		this.role = role;
	}
}
