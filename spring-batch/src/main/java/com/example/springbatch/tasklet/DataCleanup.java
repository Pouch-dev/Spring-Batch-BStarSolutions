package com.example.springbatch.tasklet;

import com.example.springbatch.exception.ApiRequestException;
import com.example.springbatch.respository.UserRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;


public class DataCleanup implements Tasklet {

    @Autowired UserRepository userRepository;
//    @Autowired
//    @Qualifier("JDBCTemplateRepository")
//    JDBCRepository repository;

    public DataCleanup(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //try catch (return status)
        try{
        userRepository.deleteAll();
        } catch (Exception ex){
            throw new ApiRequestException("Clean up fail because " + ex);
        }
        return RepeatStatus.FINISHED;
    }
}
