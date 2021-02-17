package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorsService {

	private List<Professor> profesores = new ArrayList<Professor>();

	public List<Professor> getProfessors() {
		if (profesores == null) {
			profesores.add(new Professor(1L, "122335566F", "Andrés", "Fernández", "Doctor"));
			profesores.add(new Professor(2L, "981553910A", "José", "De la Cruz", "Temporal"));
			profesores.add(new Professor(3L, "798719823G", "María", "Martínez", "Catedrática"));
		}
		return profesores;
	}

	public Professor getProfessor(Long id) {
		if (profesores != null) {
			for (Professor p : profesores) {
				if (p.getId() == id) return p;
			}
		}
		return null;
	}

	public void addProfessor(Professor prof) {
		if (prof.getId() == null) {
			// Obtener el último ID de la lista
			long last_id = profesores.get( profesores.size() -1 ).getId();
			prof.setId( last_id + 1 );
			profesores.add( prof );
		}
	}

	public void deleteProfessor(Long id) {
		for (int i= 0; i < profesores.size(); i++) {
			Professor current_prof = profesores.get(i);
			if (current_prof.getId() == id) {
				profesores.remove(i);
			}
		}
	}
}
