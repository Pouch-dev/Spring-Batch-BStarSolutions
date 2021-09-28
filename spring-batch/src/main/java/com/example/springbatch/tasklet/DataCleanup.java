package com.example.springbatch.tasklet;

import com.example.springbatch.respository.UserRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DataCleanup implements Tasklet {


    private UserRepository repository;

    public DataCleanup(UserRepository repository){
        this.repository=repository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        repository.deleteAll();

        return RepeatStatus.FINISHED;
    }
}
