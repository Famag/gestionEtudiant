package com.gestionEtudiant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gestionEtudiant.dao.IEtudiantRepository;
import com.gestionEtudiant.entity.Etudiant;

@SpringBootApplication
public class GestionEtudiantApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(GestionEtudiantApplication.class, args);
		   IEtudiantRepository etu = ctx.getBean(IEtudiantRepository.class);
		   
	/*	   DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		   
		   etu.save(new Etudiant("Gueye Fama","fama@imedia.sn", df.parse("1976-12-19"),"image.jpg"));
		   etu.save(new Etudiant("Fall Max","max@yahoo.fr", df.parse("1982-02-09"),"image1.jpg"));
		   etu.save(new Etudiant("Dabo Ibou","dabo@gmail.com", df.parse("1981-12-19"),"image.jpg"));*/
		   
		   //Page<Etudiant> etds = etu.findAll(new PageRequest(0, 2));
	/*	   Page<Etudiant> etds = etu.chercherEtudiant("%F%", new PageRequest(0, 5));
		   etds.forEach(e->System.out.println(e.getNom()));*/
	}
}
