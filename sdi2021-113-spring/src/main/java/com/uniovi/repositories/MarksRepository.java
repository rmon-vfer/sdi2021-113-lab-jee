package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

public interface MarksRepository extends CrudRepository<Mark,Long>{
	
	@Query("select r from Mark r where r.user = ?1 order by r.id asc ")
	List<Mark> findAllByUser(User user);
	
	@Modifying
	@Transactional
	@Query("update Mark set resend = ?1 where id = ?2")
	void updateResend(Boolean resend, Long id);
}