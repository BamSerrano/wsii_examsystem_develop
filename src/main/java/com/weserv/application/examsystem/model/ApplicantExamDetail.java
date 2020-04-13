package com.weserv.application.examsystem.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "applicantexamdetail")
public class ApplicantExamDetail implements Serializable {

	private static final long serialVersionUID = -8169046324100427817L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="question_id")
	private Question question;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="applicantexam_id")
	private ApplicantExam applicantExam;
	
	@Transient
	private List<Answer> answers;
	
	@Column(name = "answerId")
	private Long answerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public ApplicantExam getApplicantExam() {
		return applicantExam;
	}

	public void setApplicantExam(ApplicantExam applicantExam) {
		this.applicantExam = applicantExam;
	}
	
	
	
}
