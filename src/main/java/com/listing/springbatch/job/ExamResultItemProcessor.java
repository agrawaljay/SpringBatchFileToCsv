package com.listing.springbatch.job;

import org.springframework.batch.item.ItemProcessor;

import com.listing.springbatch.model.ExamResult;

public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult> {

	@Override
	public ExamResult process(ExamResult result) throws Exception {
		System.out.println("Processing Itemresult :"+result);
		
		//Write logic to get the data from manager and create a new pojo or update the existing one.
		result.setCheck(1);
		// TODO Auto-generated method stub
		return result;
	}

	
	
}
