package edu.gsu.ays.gpi.inoisbatch.tasks;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ProcessEntityData implements Tasklet {

    Logger log = LoggerFactory.getLogger(ProcessEntityData.class);

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        //System.out.println("ProcessEntityData start..");
        log.info("ProcessEntityData start...");

        // ... your code

        log.info("ProcessEntityData done..");
        return RepeatStatus.FINISHED;
    }
}
