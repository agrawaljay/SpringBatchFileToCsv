package com.listing.springbatch.job;


import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;


import com.listing.springbatch.model.ExamResult;

public class LineReader implements ItemReader<ExamResult> {
    
	
	
	private FileUtils fileUtils;
	private final Logger logger = LoggerFactory.getLogger(LineReader.class);
	
	
	public void setFileUtils (String fileName)
	{
		this.fileUtils=new FileUtils(fileName);
	}
	
	@Override
    public ExamResult read() throws Exception {
        ExamResult result = fileUtils.readLine();
        if (result != null) 
        	System.out.println("Read line: " + result.toString());
        return result;
    }


	
	
	
	
}