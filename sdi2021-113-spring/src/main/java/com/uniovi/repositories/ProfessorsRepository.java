package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Professor;

public interface ProfessorsRepository extends CrudRepository<Professor, Long> {
	Professor findByDni(String dni);
	
	@Query("select r from Professor r where (lower(r.nombre) like lower(%?1%) or lower(r.apellidos) like lower(%?1%))")
	List<Professor> searchByFullName(String seachtext);
}