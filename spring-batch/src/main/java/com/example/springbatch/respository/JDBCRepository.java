package com.example.springbatch.respository;

import com.example.springbatch.model.UserManagement;

import java.util.List;
import java.util.Optional;

public interface JDBCRepository {

    int save(UserManagement user);

    int update(UserManagement user);

    int deleteById(Integer id);

    List<UserManagement> findAll();

    Optional<UserManagement> findById(Integer id);
}
