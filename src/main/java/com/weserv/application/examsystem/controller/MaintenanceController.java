package com.weserv.application.examsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.weserv.application.examsystem.model.Question;
import com.weserv.application.examsystem.repository.QuestionRepository;

@Controller
@RequestMapping("/mainte")
public class MaintenanceController {
	
	@Autowired
	QuestionRepository questionRepository;
	
	private boolean isDeleteException;
	
	@RequestMapping("/home")
	public String loginPage(Model model) {
		List<Question> questionList = questionRepository.findAll();
		
		model.addAttribute("questionList", questionList);
		if (isDeleteException) {
			model.addAttribute("deleteexception", "true");
			isDeleteException = false;
		}
		
		return "home";	
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model, @RequestParam long id) {
		try {
			questionRepository.deleteById(id);
		} catch (Exception e) {
			isDeleteException = true;
		}

		return "redirect:/mainte/home";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Model model, @RequestParam long id) {
		
		Question question = questionRepository.findById(id).get();
		
		model.addAttribute("question", question);

		return "edit";
	}

}
