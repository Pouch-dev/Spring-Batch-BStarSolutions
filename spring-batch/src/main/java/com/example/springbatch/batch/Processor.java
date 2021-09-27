package com.example.springbatch.batch;

import com.example.springbatch.model.UserManagement;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<UserManagement, UserManagement> {

    private static final Map<String, String> DEPT_NAME = new HashMap<>();

    public Processor(){
        DEPT_NAME.put("001","Technology");
        DEPT_NAME.put("002","Operations");
        DEPT_NAME.put("003","Accounts");
    }

    @Override
    public UserManagement process(UserManagement userManagement) throws Exception {
        String deptCode = userManagement.getDepartments();
        String dept = DEPT_NAME.get(deptCode);
        userManagement.setDepartments(dept);
        userManagement.setTime(new Date());
        System.out.println(String.format("convert from [%s] to [%s]", deptCode, dept));
        return userManagement;
    }
}
