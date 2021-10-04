package com.example.springbatch.controller;

import com.example.springbatch.model.UserManagement;
import com.example.springbatch.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    @Qualifier("chunkJob")
    Job chunkJob;
    @Autowired
    @Qualifier("taskletJob")
    Job taskletJob;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    /**
     *
     * @return
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
    @GetMapping("/load")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);

        //this.job =chunkJob;
        JobExecution jobExecution = jobLauncher.run(chunkJob, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());
        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }

    /**
     *
     * @return
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobParametersInvalidException
     * @throws JobRestartException
     */
    @GetMapping("/cleanup")
    public BatchStatus runBatchJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);

        //this.job = taskletJob;
        JobExecution jobExecution = jobLauncher.run(taskletJob, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());
        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }



    @PostMapping("/users")
    public CompletableFuture<ResponseEntity> findAllUsers(){
        return userService.findAllUsers().thenApply(ResponseEntity::ok);
    }

    @PostMapping("/getThreadUsers")
    public ResponseEntity getListUsers(){
        CompletableFuture<List<UserManagement>> users1 =userService.findAllUsers();
        CompletableFuture<List<UserManagement>> users2 =userService.findAllUsers();
        CompletableFuture<List<UserManagement>> users3 =userService.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
