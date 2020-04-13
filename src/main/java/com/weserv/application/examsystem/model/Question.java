package com.weserv.application.examsystem.model;

import java.io.Serializable;
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
@Table(name = "question")
public class Question implements Serializable {
	
	private static final long serialVersionUID = -641637977066792268L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy="question", cascade=CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="category_id")
	private ExamCategory category;
	
	@Column(name = "answer_id")
	private Long answerId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public ExamCategory getCategory() {
		return category;
	}

	public void setCategory(ExamCategory category) {
		this.category = category;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	
}
