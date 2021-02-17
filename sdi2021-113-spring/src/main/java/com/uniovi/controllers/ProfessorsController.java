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
@Controller
public class ProfessorsController {

	@Autowired // Inyectar el servicio
	private ProfessorsService professorsService;

	// Ver listado
	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorsList", professorsService.getProfessors());
		return "professor/list"; 
	}
	
	// Añadir
	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String addProfessor( @ModelAttribute Professor prof ) {
		professorsService.addProfessor(prof);
		return "redirect:/professor/add";
	}
	
	// Ver detalle
	@RequestMapping("/professor/details/{id}")
	public String getDetail( Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorsService.getProfessor(id));
		return "professor/details";
	}
	
	// Eliminar
	@RequestMapping("/professor/delete/{id}")
	public String deleteMark(@RequestParam Long id) {
		professorsService.deleteProfessor(id);
		return "redirect:/professor/remove";
	}
	
	// Editar
	@RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Professor professor) {
		professor.setId(id);
		professorsService.addProfessor( professor );
		return "redirect:/professor/details/" + id;
	}
}
