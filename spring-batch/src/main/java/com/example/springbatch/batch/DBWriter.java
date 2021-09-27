package com.example.springbatch.batch;

import com.example.springbatch.model.UserManagement;
import com.example.springbatch.respository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<UserManagement> {

    @Autowired
    UserRepository repository;

    @Override
    public void write(List<? extends UserManagement> users) throws Exception {

//        System.out.println("Data Saved for user: " + users);
//        repository.save(users);

        for (UserManagement record : users) {
            repository.save(record);
            System.out.println("Data Saved for user: " + record.toString());
        }
//        repository.saveAll(users);
    }
}