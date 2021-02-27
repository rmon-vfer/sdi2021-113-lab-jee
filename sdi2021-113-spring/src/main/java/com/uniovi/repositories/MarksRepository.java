package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

public interface MarksRepository extends CrudRepository<Mark,Long>{
	
	@Query("select r from Mark r where (lower(r.description) like lower(?1) or	lower(r.user.name) like lower(?1))")
	Page<Mark> searchByDescriptionAndName(Pageable pageable, String seachtext);
	
	@Query("select r from Mark r where (lower(r.description) like lower(?1) or lower(r.user.name) like lower(?1)) and r.user = ?2 ")
	Page<Mark> searchByDescriptionNameAndUser(Pageable pageable, String seachtext, User user);
	
	@Query("select r from Mark r where r.user = ?1 order by r.id asc ")
	Page<Mark> findAllByUser(Pageable pageable, User user);
	
	Page<Mark> findAll(Pageable pageable); 
	
	@Modifying
	@Transactional
	@Query("update Mark set resend = ?1 where id = ?2")
	void updateResend(Boolean resend, Long id);
}