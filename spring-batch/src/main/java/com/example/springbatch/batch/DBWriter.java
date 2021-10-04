package com.example.springbatch.batch;

import com.example.springbatch.model.UserManagement;
import com.example.springbatch.respository.JDBCRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<UserManagement> {

//  @Autowired UserRepository repository;
    @Autowired
    @Qualifier("JDBCTemplateRepository")
    JDBCRepository repository;

    /**
     *
     * @param users
     * @throws Exception
     */
    @Override
    public void write(List<? extends UserManagement> users) throws Exception {

        for (UserManagement record : users) {
            repository.save(record);
            System.out.println("Data Saved for user: " + record.toString());
        }
//        repository.saveAll(users);
    }
}
