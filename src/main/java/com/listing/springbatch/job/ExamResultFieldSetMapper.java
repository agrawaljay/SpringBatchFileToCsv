package com.listing.springbatch.job;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.listing.springbatch.model.ExamResult;

public class ExamResultFieldSetMapper implements FieldSetMapper<ExamResult>{

	public ExamResult mapFieldSet(FieldSet fieldSet) throws BindException {
		ExamResult result = new ExamResult();
		result.setStudentName(fieldSet.readString(0));
		result.setDob(FileUtils.getLocalDate(fieldSet.readString(1)));
		result.setPercentage(fieldSet.readDouble(2));
		return result;
	}

}
