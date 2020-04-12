package edu.gsu.ays.gpi.inoisbatch.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ProcessHashData implements Tasklet {

    Logger log = LoggerFactory.getLogger(ProcessEntityData.class);

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        log.info("ProcessHashData start...");

        // ... your code

        log.info("ProcessHashData done..");
        return RepeatStatus.FINISHED;
    }
}
