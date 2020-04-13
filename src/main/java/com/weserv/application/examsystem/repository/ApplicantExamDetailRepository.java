package com.weserv.application.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weserv.application.examsystem.model.ApplicantExamDetail;

public interface ApplicantExamDetailRepository extends JpaRepository<ApplicantExamDetail, Long>{
	
}
