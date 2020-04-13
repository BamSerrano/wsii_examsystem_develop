package com.weserv.application.examsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weserv.application.examsystem.model.Applicant;

public interface ApplicantRepository  extends JpaRepository<Applicant, Long>{

	 Optional<Applicant> findById(Long id);
}
