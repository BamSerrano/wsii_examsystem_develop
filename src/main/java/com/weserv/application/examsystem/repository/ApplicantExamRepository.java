package com.weserv.application.examsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.weserv.application.examsystem.model.ApplicantExam;

public interface ApplicantExamRepository extends JpaRepository<ApplicantExam, Long> {

	@Query(value = "SELECT * FROM applicantexam q WHERE refno = :refId AND score IS NULL", nativeQuery = true)
	public List<ApplicantExam> findByRefNo(@Param("refId") String refId);
}
