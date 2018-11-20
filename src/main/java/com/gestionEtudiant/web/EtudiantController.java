package com.gestionEtudiant.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.RespectBinding;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gestionEtudiant.dao.IEtudiantRepository;
import com.gestionEtudiant.entity.Etudiant;

@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController  {
	
	@Autowired
	private IEtudiantRepository etudiantRepository;
	
	@RequestMapping(value="/Index")
	public String Index(Model model, 
			@RequestParam(name="page", defaultValue="0")int p,
			@RequestParam(name="motcle", defaultValue="")String mc) {
		Page<Etudiant> listE = etudiantRepository.chercherEtudiant("%"+mc+"%", 
				new PageRequest(p, 5));
		int pagescount = listE.getTotalPages();
		int[] pages = new int[pagescount];
		for(int i=0; i<pagescount; i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pageEtudiants", listE);
		model.addAttribute("pagecourante",p);
		model.addAttribute("motcle",mc);
		return "etudiants";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String AjouterEtudiant(Model model) {		
	model.addAttribute("etudiant",new Etudiant());
		return "ajoutetudiant";
	}

	@RequestMapping(value="/saveEtudiant", method=RequestMethod.POST)
	public String SaveEtudiant(@Valid Etudiant etu, BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws  Exception {		
		if(bindingResult.hasErrors()) {
			return "ajoutetudiant";
		}
		if(!(file.isEmpty())) {	etu.setPhoto(file.getOriginalFilename());}
		etudiantRepository.save(etu);
	
			if(!(file.isEmpty())) {			
			etu.setPhoto(file.getOriginalFilename());
			file.transferTo(new File("C:/tofs-etudiants/"+etu.getId()));
		}
	return "redirect:Index";
	}

	@RequestMapping(value="/getPhoto", 
			produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception{
		File f = new File("C:/tofs-etudiants/"+id);
		return IOUtils.toByteArray(new FileInputStream(f));
		
	}
	
	@RequestMapping(value="/supprimer")
	public String SupprimerEtu(Long id) {
		etudiantRepository.delete(id);
		return "redirect:Index";
	}
	
	@RequestMapping(value="/editer")
	public String EditerEtu(Long id, Model model) {
		Etudiant etu = etudiantRepository.getOne(id);
		model.addAttribute("etudiant", etu);
		return "EditerEtudiant";
	}
	
	@RequestMapping(value="/updateEtudiant", method=RequestMethod.POST)
	public String Update(@Valid Etudiant etu, BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws  Exception {		
		if(bindingResult.hasErrors()) {
			return "EditerEtudiant ";
		}
		if(!(file.isEmpty())) {	etu.setPhoto(file.getOriginalFilename());}
		etudiantRepository.save(etu);
	
			if(!(file.isEmpty())) {			
			etu.setPhoto(file.getOriginalFilename());
			file.transferTo(new File("C:/tofs-etudiants/"+etu.getId()));
		}
	return "redirect:Index";
	}
}
