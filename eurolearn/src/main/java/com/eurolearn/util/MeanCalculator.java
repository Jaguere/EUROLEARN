package com.eurolearn.util;

import java.util.List;

public class MeanCalculator {

	public static Double calculateMean(List<Double> values) {
		
		Double total = 0.0;
		
		for(Double value : values) {
			if(value == null) {
				total+=0;
			}
			total+=value;
		}
		
		return (total/values.size());
	}
	
	
}
