package com.mini_auth.authenticator_Backend.repository;
import com.mini_auth.authenticator_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String username);
}
