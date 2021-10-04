package com.example.springbatch.config;

import com.example.springbatch.model.UserManagement;
import com.example.springbatch.respository.UserRepository;
import com.example.springbatch.tasklet.DataCleanup;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableBatchProcessing
@EnableAsync
public class SpringBatchConfig {

//    @Bean
//    public ThreadPoolTaskExecutor taskExecutor()
//    {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(5);
//        return executor;
//    }

//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor("spring_batch");
//    }

    /**
     *
     * @param jobBuilderFactory
     * @param stepBuilderFactory
     * @param itemReader
     * @param itemProcessor
     * @param itemWriter
     * @return
     */
    @Bean("chunkJob")
    @Primary
    public Job chunkJob(JobBuilderFactory jobBuilderFactory,
                        StepBuilderFactory stepBuilderFactory,
                        ItemReader<UserManagement> itemReader,
                        ItemProcessor<UserManagement, UserManagement> itemProcessor,
                        ItemWriter<UserManagement> itemWriter){

                Step chunkstep = stepBuilderFactory.get("ETL-file-load")
                        .<UserManagement, UserManagement>chunk(100)
                        .reader(itemReader)
                        .processor(itemProcessor)
                        .writer(itemWriter)
                        .taskExecutor((TaskExecutor) taskExecutor())
                        .throttleLimit(10)
                        .build();

                return jobBuilderFactory.get("ETL-file-load")
                        .incrementer(new RunIdIncrementer())
                        .start(chunkstep)

                        .build();
    }



    /**
     *
     * @param jobBuilderFactory
     * @param stepBuilderFactory
     * @param userRepository
     * @return
     */
    @Bean("taskletJob")
    public Job taskletJob(JobBuilderFactory jobBuilderFactory,
                          StepBuilderFactory stepBuilderFactory,
                          UserRepository userRepository){

                Step taskletstep = stepBuilderFactory.get("ETL-file-cleanup")
                        .tasklet(cleaningStep(userRepository))
                        .build();

                return jobBuilderFactory.get("ETL-file-cleanup")
                        .incrementer(new RunIdIncrementer())
                        .start(taskletstep)
                        .build();
    }

    /**
     *
     * @param userRepository
     * @return
     */
    @Bean
    public Tasklet cleaningStep(UserRepository userRepository) {
        return new DataCleanup(userRepository);
    }

    /**
     *
     * @return
     */
    @Bean
    public FlatFileItemReader<UserManagement> itemReader(){

        FlatFileItemReader<UserManagement> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/users.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    /**
     *
     * @return
     */
    @Bean
    public LineMapper<UserManagement> lineMapper() {
        DefaultLineMapper<UserManagement> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id","name","departments","salary"});

        BeanWrapperFieldSetMapper<UserManagement> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UserManagement.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("userThread- ");
        executor.initialize();
        return executor;
    }
}
