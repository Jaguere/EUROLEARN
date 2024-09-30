package com.eurolearn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitorpresenca")
public class MonitorPresencaController {

	@GetMapping
	public String monitorar() {
		return "monitorpresenca/monitor-presenca";
	}
	
	
}
