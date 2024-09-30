package com.eurolearn.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eurolearn.dto.FeedbackDTO;
import com.eurolearn.services.FeedbackService;
import com.eurolearn.util.MeanCalculator;

@RestController
@RequestMapping("/restfeedbacks")
public class RESTFeedbackController {

	
	@Autowired
	public FeedbackService service;
	
	@GetMapping()
	public ResponseEntity<List<FeedbackDTO>> findAll(){
		List<FeedbackDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/groupbylast12months")
	public ResponseEntity<ArrayList<ArrayList<Object>>> groupByLast12Months(){
		
		
		LocalDate startDate = LocalDate.now().minusMonths(12);
		LocalDate today = LocalDate.now();
		
		ArrayList<LocalDate> last12Months = new ArrayList<LocalDate>(startDate.datesUntil(today, Period.ofMonths(1)).toList());
		
		ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();
		
		ArrayList<Object> dates = new ArrayList<Object>();
		ArrayList<Object> values = new ArrayList<Object>();
		
		ListIterator<LocalDate> last12MonthsIterator = last12Months.listIterator();
		
		while(last12MonthsIterator.hasNext()) {
			
			LocalDate nextMonth = last12MonthsIterator.next();
			
			System.out.println("Next Month"+nextMonth);
			
			List<Double> ratingsByMonth = new ArrayList<Double>();
			
			for(FeedbackDTO dto : service.findAll()) {
						
				if(dto.getDataFeedback().toLocalDate().getMonth() == nextMonth.getMonth() && dto.getDataFeedback().toLocalDate().getYear() == nextMonth.getYear()) {
					ratingsByMonth.add(dto.getIndiceSatisfacao());
				}
					
			}
			
			if(MeanCalculator.calculateMean(ratingsByMonth).isNaN()) {
				values.add(0);
			} else {
				values.add(MeanCalculator.calculateMean(ratingsByMonth));
			}
			
			dates.add(nextMonth);
			}
		
		results.add(dates);
		results.add(values);
		
		return ResponseEntity.ok(results);
	}
	
	@GetMapping("/groupbylast6months")
	public ResponseEntity<ArrayList<ArrayList<Object>>> groupByLast6Months(){
		
		
		LocalDate startDate = LocalDate.now().minusMonths(6);
		LocalDate today = LocalDate.now();
		
		ArrayList<LocalDate> last6Months = new ArrayList<LocalDate>(startDate.datesUntil(today, Period.ofMonths(1)).toList());
		
		ArrayList<ArrayList<Object>> results = new ArrayList<ArrayList<Object>>();
		
		ArrayList<Object> dates = new ArrayList<Object>();
		ArrayList<Object> values = new ArrayList<Object>();
		
		ListIterator<LocalDate> last6MonthsIterator = last6Months.listIterator();
		
		while(last6MonthsIterator.hasNext()) {
			
			LocalDate nextMonth = last6MonthsIterator.next();
			
			System.out.println("Next Month"+nextMonth);
			
			List<Double> ratingsByMonth = new ArrayList<Double>();
			
			for(FeedbackDTO dto : service.findAll()) {
						
				if(dto.getDataFeedback().toLocalDate().getMonth() == nextMonth.getMonth() && dto.getDataFeedback().toLocalDate().getYear() == nextMonth.getYear()) {
					ratingsByMonth.add(dto.getIndiceSatisfacao());
				}
					
			}
			
			if(MeanCalculator.calculateMean(ratingsByMonth).isNaN()) {
				values.add(0);
			} else {
				values.add(MeanCalculator.calculateMean(ratingsByMonth));
			}
			
			dates.add(nextMonth);
			}
		
		results.add(dates);
		results.add(values);
		
		return ResponseEntity.ok(results);
	}
	
	
	
}
