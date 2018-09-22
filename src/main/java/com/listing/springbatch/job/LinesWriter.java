package com.listing.springbatch.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import com.listing.springbatch.model.ExamResult;

import java.util.List;

public class LinesWriter implements ItemWriter<ExamResult>, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(LinesWriter.class);
    private FileUtils fu;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        fu = new FileUtils("xml/output.csv");
        System.out.println("Line Writer initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fu.closeWriter();
        System.out.println("Line Writer ended.");
        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(List<? extends ExamResult> lines) throws Exception {
        for (ExamResult line : lines) {
            fu.writeLine(line);
            System.out.println("Wrote line " + line.toString());
        }
    }
}