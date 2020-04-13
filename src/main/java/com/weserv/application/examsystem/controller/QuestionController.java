package com.weserv.application.examsystem.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weserv.application.examsystem.model.Answer;
// import com.weserv.application.examsystem.model.Applicant;
import com.weserv.application.examsystem.model.ApplicantExam;
import com.weserv.application.examsystem.model.ApplicantExamDetail;
// import com.weserv.application.examsystem.model.ExamCategory;
import com.weserv.application.examsystem.model.Question;
import com.weserv.application.examsystem.repository.AnswerRepository;
import com.weserv.application.examsystem.repository.ApplicantExamDetailRepository;
import com.weserv.application.examsystem.repository.ApplicantExamRepository;
// import com.weserv.application.examsystem.repository.ApplicantRepository;
import com.weserv.application.examsystem.repository.ExamCategoryRepository;
import com.weserv.application.examsystem.repository.QuestionRepository;

@Controller
@RequestMapping("/exam")
public class QuestionController {

	private ApplicantExam applicantExam = new ApplicantExam();
	private List<ApplicantExam> applicantExamList;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	ApplicantExamDetailRepository applicantExamDetailRepository;
	
	@Autowired
	ApplicantExamRepository applicantExamRepository;
	
	@Autowired
	ExamCategoryRepository examCategoryRepository;
	
	//login page
	@RequestMapping("/login")
	public String loginPage(Model model) {
		return "login";	
	}
	
	//post login page
	@PostMapping("/login")
	public String checkUser(@ModelAttribute ApplicantExam applicantExam, Model model) {

		applicantExamList = applicantExamRepository.findByRefNo(applicantExam.getRefNo());
		if (applicantExamList.isEmpty()) {
			model.addAttribute("loginError", true);
			return "login";
		}
		this.applicantExam.setRefNo(applicantExam.getRefNo());
		for (ApplicantExam applicant : applicantExamList) {
			this.applicantExam.setApplicant(applicant.getApplicant());
		}
		return "redirect:/exam/selection";
	}
	
	//category selection page
	@RequestMapping("/selection")
	public String selectionPage(Model model) {
		
		model.addAttribute("applicantExam", this.applicantExam);
		model.addAttribute("examCt", applicantExamList);
		return "selection";
	}
	
	@RequestMapping("/intro/{category}")
	public String openIntroPage(Model model, @PathVariable("category") int category) {
		
		this.applicantExam = applicantExamList.get(category);

		return "redirect:/exam/intro";
	}
	
	@RequestMapping("/intro")
	public String openIntroPage(Model model) {

		model.addAttribute("applicantExam", this.applicantExam);
		return "intro";
	}

	@RequestMapping("/question")
	public String welcome(Model model) {
		// Add detail list
		List<ApplicantExamDetail> details = new ArrayList<>();

		List<Question> questionList = questionRepository.findByCategoryId(this.applicantExam.getCategory().getId(), 
				this.applicantExam.getQuestionAmt());
		for (Question question : questionList) {
			ApplicantExamDetail detailquestion = new ApplicantExamDetail();
			detailquestion.setQuestion(question);

			List<Answer> answerList = answerRepository.findByQuestionId(question.getId());
			detailquestion.setAnswers(answerList);
			details.add(detailquestion);
		}

		this.applicantExam.setDetails(details);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, this.applicantExam.getTimelimit());
		date = cal.getTime();

		model.addAttribute("applicantExam", this.applicantExam);
		model.addAttribute("count", this.applicantExam.getDetails().size());

		model.addAttribute("expiryDate", formatter.format(date));
		return "question";
	}

	@PostMapping("/save")
	public String saveAnswers(@ModelAttribute ApplicantExam applicantExam, Model model) {

		int countScore = 0;
		for (int x = 0; x < applicantExam.getDetails().size(); x++) {
			Question question = new Question();
			question.setId(applicantExam.getDetails().get(x).getQuestion().getId());

			if (applicantExam.getDetails().get(x).getAnswerId() != null) {

				if (applicantExam.getDetails().get(x).getAnswerId()
						.equals(this.applicantExam.getDetails().get(x).getQuestion().getAnswerId())) {
					countScore++;
				}
				this.applicantExam.getDetails().get(x).setAnswerId(applicantExam.getDetails().get(x).getAnswerId());			
			}
			this.applicantExam.getDetails().get(x).setApplicantExam(this.applicantExam);
		}

		this.applicantExam.setScore(countScore);
		applicantExamRepository.save(this.applicantExam);

		applicantExamList = applicantExamRepository.findByRefNo(this.applicantExam.getRefNo());
		if (applicantExamList.isEmpty()) {
			return "redirect:/exam/login";
		}

		model.addAttribute("applicantExam", this.applicantExam);
		return "redirect:/exam/selection";
	}

}
