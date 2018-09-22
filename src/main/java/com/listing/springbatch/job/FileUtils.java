package com.listing.springbatch.job;

import javax.sound.sampled.Line;

import com.listing.springbatch.model.ExamResult;
import com.opencsv.CSVReader;



import com.opencsv.CSVWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FileUtils {

    private final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private String fileName;
    private CSVReader CSVReader;
    private CSVWriter CSVWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    public ExamResult readLine() {
        try {
            if (CSVReader == null) initReader();
            String[] line = CSVReader.readNext();
            if (line == null) return null;
            return new ExamResult(line[0].trim(), getLocalDate(line[1].trim()),getDouble(line[2].trim()));
        } catch (Exception e) {
        	System.out.println("Error while reading line in file: " + this.fileName);
            return null;
        }
    }

    public void writeLine(ExamResult result) {
        try {
            if (CSVWriter == null) initWriter();
            String[] lineStr = new String[2];
            lineStr[0] = result.getStudentName();
            lineStr[1] = result.getPercentage().toString();
            CSVWriter.writeNext(lineStr);
        } catch (Exception e) {
        	System.out.println("Error while writing line in file: " + this.fileName);
        }
    }

    private void initReader() throws Exception {
        ClassLoader classLoader = this
          .getClass()
          .getClassLoader();
        if (file == null) file = new File(classLoader
          .getResource(fileName)
          .getFile());
        if (fileReader == null) fileReader = new FileReader(file);
        if (CSVReader == null) CSVReader = new CSVReader(fileReader,'|');
    }

    private void initWriter() throws Exception {
        if (file == null) {
            file = new File(fileName);
            file.createNewFile();
        }
        if (fileWriter == null) fileWriter = new FileWriter(file, true);
        if (CSVWriter == null) CSVWriter = new CSVWriter(fileWriter);
    }

    public void closeWriter() {
        try {
            CSVWriter.close();
            fileWriter.close();
        } catch (IOException e) {
        	System.out.println("Error while closing writer.");
        }
    }

    public void closeReader() {
        try {
            CSVReader.close();
            fileReader.close();
        } catch (IOException e) {
        	System.out.println("Error while closing reader.");
        }
    }
    
    
    
    public static LocalDate getLocalDate(String date)
    { 	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;

    }
    
    public static double getDouble(String num)
    {
    	return Double.parseDouble(num);
    }

}