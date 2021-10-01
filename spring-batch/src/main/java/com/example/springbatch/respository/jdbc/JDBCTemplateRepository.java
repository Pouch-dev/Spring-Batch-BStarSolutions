package com.example.springbatch.respository.jdbc;

import com.example.springbatch.model.UserManagement;
import com.example.springbatch.respository.JDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JDBCTemplateRepository implements JDBCRepository {

    @Autowired private JdbcTemplate jdbcTemplate;

    @Override
    public int save(UserManagement user) {
        return jdbcTemplate
                .update("insert into user_management (departments,name,salary,time,executor) values(?,?,?,?,?)",
                user.getDepartments(),user.getName(),user.getSalary(),user.getTime(),user.getExecutor());
    }

    @Override
    public int update(UserManagement user) {
        return jdbcTemplate.update(
                "update user_management set departments = ?,name = ?,salary = ? where id = ?",
                user.getDepartments(),user.getName(),user.getSalary());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update(
                "delete user_management where id = ?", id);
    }

    @Override
    public List<UserManagement> findAll() {
        return jdbcTemplate.query(
                "select * from user_management",
                (result, row) -> new UserManagement(
                        result.getInt("id"),
                        result.getString("departments"),
                        result.getString("name"),
                        result.getString("salary"),
                        result.getDate("time"),
                        result.getString("executor"))
        );
    }

    @Override
    public Optional<UserManagement> findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from user_management where id = ?",
                new Object[]{id},
                (result, row) -> Optional.of(new UserManagement(
                        result.getInt("id"),
                        result.getString("departments"),
                        result.getString("name"),
                        result.getString("salary"),
                        result.getDate("time"),
                        result.getString("executor")
                ))
        );
    }
}
