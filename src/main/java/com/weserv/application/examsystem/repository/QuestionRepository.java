package com.weserv.application.examsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weserv.application.examsystem.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query(value = "SELECT * FROM question q WHERE category_id = :categoryId ORDER BY random() LIMIT :limit",
			nativeQuery = true)
	public List<Question> findByCategoryId(@Param("categoryId") Long categoryId, @Param("limit") Integer limit);

}
