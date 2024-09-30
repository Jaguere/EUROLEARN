package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eurolearn.services.MonitorPresencaService;

@RestController
@RequestMapping("/REST/monitorar")
public class RESTMonitorarPresencaController {

	@Autowired
	private MonitorPresencaService service;
	
	@GetMapping("/taxaPresenca")
	public ResponseEntity calcularTaxaPresenca(){
		return ResponseEntity.ok(service.calcularTaxaPresenca());
	}
	
	@GetMapping("/data")
	public ResponseEntity<List<Integer>> getConfirmacaoData(){
		return ResponseEntity.ok(service.getData());
	}
	
}
