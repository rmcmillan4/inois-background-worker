package edu.gsu.ays.gpi.inoisbatch.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ProcessEntityData implements Tasklet {

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        System.out.println("ProcessEntityData start..");

        // ... your code

        System.out.println("ProcessEntityData done..");
        return RepeatStatus.FINISHED;
    }
}
