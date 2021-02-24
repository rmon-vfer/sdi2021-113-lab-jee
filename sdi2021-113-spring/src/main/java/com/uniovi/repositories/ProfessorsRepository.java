package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.uniovi.entities.Professor;

public interface ProfessorsRepository extends CrudRepository<Professor, Long> {
	Professor findByDni(String dni);
}