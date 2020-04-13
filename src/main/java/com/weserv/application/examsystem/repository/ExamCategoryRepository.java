package com.weserv.application.examsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weserv.application.examsystem.model.ExamCategory;

public interface ExamCategoryRepository extends JpaRepository<ExamCategory, Long> {

}
