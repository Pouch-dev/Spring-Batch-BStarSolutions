package com.example.springbatch.batch;

import com.example.springbatch.model.UserManagement;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<UserManagement, UserManagement> {

    private static final Map<String, String> DEPT_NAME = new HashMap<>();

    /**
     *
     * @return
     */
//    @Bean
//    public TaskExecutor taskExecutor()
//    {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.getCorePoolSize();
//        executor.getMaxPoolSize();
//        executor.getActiveCount();
//        return new SimpleAsyncTaskExecutor("spring_batch");
//    }

    public Processor(){
        DEPT_NAME.put("001","Technology");
        DEPT_NAME.put("002","Operations");
        DEPT_NAME.put("003","Accounts");
    }

    /**
     *
     * @param userManagement
     * @return
     * @throws Exception
     */
    @Override
    public UserManagement process(UserManagement userManagement) throws Exception {
        String deptCode = userManagement.getDepartments();
        String dept = DEPT_NAME.get(deptCode);
        userManagement.setDepartments(dept);
        userManagement.setTime(new Date());
//        userManagement.setExecutor(String.valueOf(
//                        "Task Exceptor: "+taskExecutor()
//                        "Core Pool Size: "+taskExecutor().getCorePoolSize()+
//                        ", Max Pool Size: "+taskExecutor().getMaxPoolSize()+
//                        ", Task count: "+taskExecutor().getThreadPoolExecutor().getTaskCount()+
//                        ", Queue Capacity: "+taskExecutor().getThreadPoolExecutor().getQueue()+
//                        ", ShutDown: "+taskExecutor().getThreadPoolExecutor().isShutdown()+
//                        ", Terminated: "+taskExecutor().getThreadPoolExecutor().isTerminated()
//        ));
        System.out.println(String.format("convert from [%s] to [%s]", deptCode, dept));
        return userManagement;
    }
}
