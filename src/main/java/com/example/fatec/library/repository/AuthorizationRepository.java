package com.example.fatec.library.repository;

import com.example.fatec.library.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service("AuthorizationRepository")
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    Authorization findByName(String name);
}
