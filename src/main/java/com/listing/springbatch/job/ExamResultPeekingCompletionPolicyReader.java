package com.listing.springbatch.job;



import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;

import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.context.RepeatContextSupport;
import org.springframework.batch.repeat.policy.CompletionPolicySupport;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;

import com.listing.springbatch.model.ExamResult;

public class ExamResultPeekingCompletionPolicyReader extends CompletionPolicySupport 
implements ItemReader<ExamResult>, StepExecutionListener {

    private SingleItemPeekableItemReader<ExamResult> delegate;
    private ComparisonPolicyTerminationContext terminationContext;
      
    private ExamResult currentReadItem = null;
    

    public void setDelegate (LineReader reader){
    	this.delegate = new SingleItemPeekableItemReader<ExamResult>();
    	this.delegate.setDelegate (reader);
    }
    
    @Override
    public boolean isComplete(RepeatContext context) {
      return this.terminationContext.isComplete();
    }

    @Override
    public ExamResult read() throws UnexpectedInputException, ParseException, NonTransientResourceException, Exception {
        currentReadItem = delegate.read();
        return currentReadItem;
    }
    
    
    @Override
    public RepeatContext start(final RepeatContext context) {
		currentReadItem = invokePeek();
    	this.terminationContext = new ComparisonPolicyTerminationContext(context);
        return terminationContext;
    }
    
    
    private ExamResult invokePeek() {
    	ExamResult peeked = null;
        try {
          peeked = delegate.peek();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return peeked;
      }

    
    @Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		  System.out.println("Step initialized.");
		
	}

    @Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Step ended.");
        return ExitStatus.COMPLETED;
	}

    protected class ComparisonPolicyTerminationContext extends RepeatContextSupport {

        public ComparisonPolicyTerminationContext(final RepeatContext context) {
            super(context);
        }

        public boolean isComplete() {
		//	System.out.println("Inside isComplete() to peek the next record ");
            ExamResult nextReadItem=null;
			nextReadItem = invokePeek();
			
            // If next row is null or the data does not match then end chunk
            if (null==nextReadItem || !currentReadItem.isSameResult(nextReadItem)) {
            	currentReadItem=nextReadItem;
            	return true;
            }

            return false;
        }
    }


}