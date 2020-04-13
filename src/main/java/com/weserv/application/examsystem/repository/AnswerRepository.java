package com.weserv.application.examsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weserv.application.examsystem.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	List<Answer> findByQuestionId(Long id);
	
}
