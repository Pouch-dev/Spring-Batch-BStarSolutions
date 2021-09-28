package com.example.springbatch.job;

import com.example.springbatch.respository.UserRepository;
import com.example.springbatch.tasklet.DataCleanup;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskLetConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private UserRepository userRepository;

    @Autowired
    public TaskLetConfig(JobBuilderFactory jobBuilderFactory,
                         StepBuilderFactory stepBuilderFactory,
                         UserRepository repository){
        this.jobBuilderFactory =jobBuilderFactory;
        this.stepBuilderFactory=stepBuilderFactory;
        this.userRepository=repository;
    }

    @Qualifier(value = "tasklet")
    @Bean
    public Job taskletJob() throws  Exception{
        return this.jobBuilderFactory.get("tasklet")
                .start(stepTasklet())
                .build();
    }

    @Bean
    public Step stepTasklet() throws Exception{
        return stepBuilderFactory.get("step1")
                .tasklet(new DataCleanup(userRepository))
                .build();
    }
}
