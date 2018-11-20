package com.gestionEtudiant.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionEtudiant.entity.Etudiant;

public interface IEtudiantRepository extends JpaRepository<Etudiant, Long>{
	
	// une méthode ki permet de chercher les étudiants par Nom
	public List<Etudiant> findByNom(String nom);

	// une méthode ki permet de chercher les étudiants par Nom afficher page par page
	
	public Page<Etudiant> findByNom(String nom, Pageable page);
	
	// une méthode ki permet de chercher les étudiants par Nom afficher page par page
	@Query("select e from Etudiant e where e.nom like :x")
		public Page<Etudiant> chercherEtudiant(@Param("x")String mc, Pageable page);
	
	@Query("select e from Etudiant e where e.dateNaissance >:x and e.dateNaissance <:y")
	public List<Etudiant> chercherEtudiant(@Param("x")Date d1, @Param("y")Date d2);
}
