package com.uniovi.controllers;

import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Mark;

/*
 * Spring trata todas las peticiones como GET por defecto,
 * para responder a peticiones POST hay que especificarlo
 * en la anotación @RequestMapping
 */

@RestController
public class MarksController {

	@RequestMapping("/mark/list")
	public String getList() {
		return "Getting List";
	}

	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		return "added: " + mark.getDescription() + " with score : " + mark.getScore() + " id: " + mark.getId();
	}

	@RequestMapping("/mark/details/{id}")
	/* Alternativa: @RequestParam */
	public String getDetail(@PathVariable Long id) {
		return "Getting Details:" + id;
	}
}
