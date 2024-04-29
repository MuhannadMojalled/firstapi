package com.example.demo.DAO;

import com.example.demo.Entities.UserEntity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
        UserEntity findByEmail(String email);
}
