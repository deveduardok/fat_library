package com.example.fatec.library.repository;

import com.example.fatec.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
