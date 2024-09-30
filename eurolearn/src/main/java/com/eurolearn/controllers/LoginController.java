package com.eurolearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eurolearn.util.LoginInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping
	public String enterApplication(Model model, LoginInfo loginInfo) {
		model.addAttribute("loginInfo", loginInfo);
		return "login/login";
	}
	
	@PostMapping
	public String redirectApplication(LoginInfo loginInfo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", loginInfo.getCpf());
		return "redirect:/treinamentos";
	}

}
