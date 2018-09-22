package com.listing.springbatch.job;

import org.springframework.batch.item.ItemProcessor;

import com.listing.springbatch.model.ExamResult;

public class ExamResultItemFilterProcessor implements ItemProcessor<ExamResult, ExamResult>{

	
	public ExamResult process(ExamResult result) throws Exception {
		System.out.println("Processing Filter result :"+result);
		
		/*
		 * Only return results which are more than 60%
		 * 
		 */
		if(result.getPercentage() < 60){
			return null;
		}
		
		return result;
	}

}
