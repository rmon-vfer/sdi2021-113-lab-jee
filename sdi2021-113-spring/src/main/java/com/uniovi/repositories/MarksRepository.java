package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Mark;

public interface MarksRepository extends CrudRepository<Mark, Long> {
	
	@Modifying
	@Transactional
	@Query("update Mark set resend = ?1 where id = ?2")
	void updateResend(Boolean resend, Long id);
}