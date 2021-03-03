package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorsRepository;

@Service
public class ProfessorsService {

	@Autowired
	private ProfessorsRepository professorsRepository;

	public List<Professor> getProfessors() {
		List<Professor> professors = new ArrayList<Professor>();
		professorsRepository.findAll().forEach(professors::add);
		return professors;
	}

	public Professor getProfessor(Long dni) {
		return professorsRepository.findById(dni).get();
	}

	public void addProfessor(Professor prof) {
		professorsRepository.save(prof);
	}

	public void deleteProfessor(Long id) {
		professorsRepository.deleteById(id);
	}

	public Professor getProfessorByDni(String dni) {
		return professorsRepository.findByDni(dni);
	}

	public List<Professor> searchByFullName(String searchText) {
		List<Professor> professors = new ArrayList<Professor>();
		professors = professorsRepository.searchByFullName(searchText);

		return professors;
	}
}