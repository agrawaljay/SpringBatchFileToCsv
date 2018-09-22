package com.listing.springbatch.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ExamResult implements Serializable{

	private String studentName;

	private LocalDate dob;

	private Double percentage;
	
	private int check;
	
	public ExamResult(){}
	public ExamResult(String studentName, LocalDate dob, double percentage)
	{
		this.setStudentName(studentName);
		this.setDob(dob);
		this.setPercentage(percentage);
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "ExamResult [studentName=" + studentName + ", dob=" + dob
				+ ", percentage=" + percentage + "]";
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
	
	public boolean isSameResult (ExamResult nextResult)
	{
		return this.getPercentage().doubleValue()==nextResult.getPercentage().doubleValue();
	}

}
