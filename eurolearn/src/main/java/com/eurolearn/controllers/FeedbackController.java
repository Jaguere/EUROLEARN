package com.eurolearn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.FeedbackDTO;
import com.eurolearn.services.FeedbackService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

	@Autowired
	private FeedbackService service;
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("feedbacks", service.findAll());
		return "feedback/listar-feedbacks"; 
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") int id, Model model) {
		model.addAttribute("feedbackDTO", service.findById(id));
		return "/feedback/editar-feedback";
	}
	
	@GetMapping("/form")
	public String loadForm(Model model) {
		model.addAttribute("feedbackDTO", new FeedbackDTO());
		return "feedback/novo-feedback";
	}
	
	@GetMapping("/dashboard")
	public String openGraphs() {
		return "feedback/graficos-feedbacks";
	}
	
	@PostMapping()
	public String insert(@Valid FeedbackDTO dto, RedirectAttributes attributes, BindingResult result) {
		if(result.hasErrors()) {
			return "feedback/novo-feedback";
		}
		dto = service.insert(dto);
		attributes.addFlashAttribute("mensagem", "Feedback salvo com sucesso"); 
		return "redirect:/feedbacks";
	}
	
	@PutMapping("/{id}")
	public String update(@Valid FeedbackDTO dto, BindingResult result, @PathVariable("id") int id) {
		if(result.hasErrors()) {
			dto.setIdFeedback(id); 
			return "feedback/editar-feedback";
		}
		service.update(id, dto);
		return "redirect:/feedbacks";
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		return "redirect:/feedbacks";
	}
	
}
