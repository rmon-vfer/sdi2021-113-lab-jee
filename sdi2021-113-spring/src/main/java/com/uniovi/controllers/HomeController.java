package com.uniovi.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}