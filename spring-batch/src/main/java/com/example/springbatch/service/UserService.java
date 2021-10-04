package com.example.springbatch.service;

import com.example.springbatch.model.UserManagement;
import com.example.springbatch.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    Object target;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Async
    public CompletableFuture<List<UserManagement>> saveUser(MultipartFile file) throws Exception{
        long start=System.currentTimeMillis();
        List<UserManagement> users=parseCSVFile(file);
        logger.info("saving list of size {}",users.size(),""+Thread.currentThread().getName());
        users=repository.saveAll(users);
        long end=System.currentTimeMillis();
        logger.info("Total time {}", (end-start));
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<List<UserManagement>> findAllUsers(){
        logger.info("get list of user by "+Thread.currentThread().getName());
        List<UserManagement> users=repository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    private List<UserManagement> parseCSVFile (final MultipartFile file) throws Exception{
        final List<UserManagement> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                String line;
                while ((line=br.readLine()) != null){
                    final String[] data = line.split(",");
                    final UserManagement user = new UserManagement();
                    user.setName(data[0]);
                    user.setDepartments(data[1]);
                    user.setSalary(data[2]);
                    users.add(user);
                }
                return users;
            }
        }catch (Exception e){
            logger.error("Failed to parse CSV file{}",e);
            throw new Exception("Failed to parse CSV file{}",e);
        }
    }
}
