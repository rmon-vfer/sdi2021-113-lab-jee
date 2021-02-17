package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Professor;
import com.uniovi.services.MarksService;
import com.uniovi.services.ProfessorsService;

/*
 * Spring trata todas las peticiones como GET por defecto,
 * para responder a peticiones POST hay que especificarlo
 * en la anotación @RequestMapping */
@RestController
public class ProfessorsController {

	@Autowired // Inyectar el servicio
	private ProfessorsService professorsService;

	// Ver listado
	@RequestMapping("/professors/list")
	public String getList() {
		StringBuffer sb = new StringBuffer();
		List<Professor> current_professors = professorsService.getProfessors();
		
		sb.append( "Professors on database: " + current_professors.size() + "\n");
		for (Professor p : current_professors) {
			sb.append(p.toString() + "\n");
		}
		return sb.toString();
	}
	
	// Añadir
	@RequestMapping(value = "/professors/add", method = RequestMethod.POST)
	public String addProfessor( @ModelAttribute Professor prof ) {
		professorsService.addProfessor(prof);
		return "Added professor with dni: " + prof.getDni();
	}
	
	// Ver detalle
	@RequestMapping("/professors/details/{id}")
	public String getDetail( @PathVariable Long id) {
		return "Details for professor with id " + id + ": " +  professorsService.getProfessor(id).toString();
	}
	
	// Eliminar
	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		professorsService.deleteProfessor(id);
		return "Deleted professor with id " + id;
	}
	
	// Editar
	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
	public String setEdit(@PathVariable Long id, @ModelAttribute Professor professor) {
		professorsService.deleteProfessor(id);
		professor.setId(id);
		professorsService.addProfessor(professor);
		return "Modified professor with id " + id;
	}
}
