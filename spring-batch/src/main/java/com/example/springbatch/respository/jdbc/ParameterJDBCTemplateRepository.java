package com.example.springbatch.respository.jdbc;

import com.example.springbatch.model.UserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ParameterJDBCTemplateRepository extends JDBCTemplateRepository {

    @Autowired
    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    @Override
    public int update(UserManagement user) {
        return parameterJdbcTemplate.update(
                "update user_management set " +
                        "departments = :departments," +
                        "name = :name," +
                        "salary = :salary " +
                        "where id = :id",
                new BeanPropertySqlParameterSource(user));
    }

    @Override
    public Optional<UserManagement> findById(Integer id) {
        return parameterJdbcTemplate.queryForObject(
                "select * from user_management where id = :id",
                new MapSqlParameterSource("id",id),
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
