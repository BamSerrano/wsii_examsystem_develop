package com.weserv.application.examsystem.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "applicantexam")
public class ApplicantExam implements Serializable {

	private static final long serialVersionUID = 8487775529296431117L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "refno")
	private String refNo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="applicant_id")
	private Applicant applicant;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	@JoinColumn(name="category_id")
	private ExamCategory category;
	
	@Column(name = "schedule")
	private Timestamp schedule;
	
	@OneToMany(mappedBy="applicantExam", cascade=CascadeType.ALL)
	private List<ApplicantExamDetail> details = new ArrayList<>();
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "timelimit")
	private Integer timelimit;
	
	@Column(name = "question_amt")
	private Integer questionAmt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public ExamCategory getCategory() {
		return category;
	}

	public void setCategory(ExamCategory category) {
		this.category = category;
	}

	public Timestamp getSchedule() {
		return schedule;
	}

	public void setSchedule(Timestamp schedule) {
		this.schedule = schedule;
	}

	public List<ApplicantExamDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ApplicantExamDetail> details) {
		this.details = details;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getTimelimit() {
		return timelimit;
	}
	
	public void setTimelimit(Integer timelimit) {
		this.timelimit = timelimit;
	}
	
	public Integer getQuestionAmt() {
		return questionAmt;
	}
	
	public void setQuestionAmt(Integer questionAmt) {
		this.questionAmt = questionAmt;
	}
}
