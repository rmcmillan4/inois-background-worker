package edu.gsu.ays.gpi.inoisbatch.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ProcessHashData implements Tasklet {

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        System.out.println("ProcessHashData start..");

        // ... your code

        System.out.println("ProcessHashData done..");
        return RepeatStatus.FINISHED;
    }
}
