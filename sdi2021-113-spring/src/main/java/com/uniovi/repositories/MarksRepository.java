package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

public interface MarksRepository extends CrudRepository<Mark,Long>{
	
	@Query("select r from Mark r where r.user = ?1 ORDER BY r.id ASC ")
	List<Mark> findAllByUser(User user);
	
	@Query("select r from Mark r where (lower(r.description) like lower(?1) or lower(r.user.name) like lower(?1))")
	List<Mark> searchByDescriptionAndName(String searchText);
	
	@Query("select r from Mark r where (lower(r.description) like lower(?1) or lower(r.user.name) like lower(?1)) and r.user = ?2 ")
	List<Mark> searchByDescriptionNameAndUser(String searchText, User user);

	@Modifying
	@Transactional
	@Query("update Mark set resend = ?1 where id = ?2")
	void updateResend(Boolean resend, Long id);
}