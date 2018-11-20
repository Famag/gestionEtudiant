package com.gestionEtudiant.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Etudiant   implements Serializable{
		
   @Id
   @GeneratedValue
	private Long id;
   @Column(name="Nom", length=30)
   @NotEmpty
   @Size(min=3, max=10)
	private String nom;
   @NotEmpty
   @Email(message="Ceci n'est pas une adresse email")
   	private String email;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissance;
	private String photo;
	
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Etudiant(String nom, String email, Date dateNaissance, String photo) {
		super();
		this.nom = nom;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	

}
