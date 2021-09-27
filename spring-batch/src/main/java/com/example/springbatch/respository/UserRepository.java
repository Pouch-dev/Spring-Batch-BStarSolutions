package com.example.springbatch.respository;

import com.example.springbatch.model.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserManagement, Integer> {
}
