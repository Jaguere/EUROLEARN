package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eurolearn.services.RankingService;
import com.eurolearn.util.RankingMember;

@RestController
@RequestMapping("/REST/ranking")
public class RESTRankingController {
	
	@Autowired
	private RankingService service;
	
	@GetMapping
	public ResponseEntity<List<RankingMember>> getRanking(){
		return ResponseEntity.ok(service.buildRanking());
	}

}
