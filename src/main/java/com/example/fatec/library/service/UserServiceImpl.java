package com.example.fatec.library.service;

import com.example.fatec.library.dto.UserDto;
import com.example.fatec.library.entity.Authorization;
import com.example.fatec.library.entity.User;
import com.example.fatec.library.repository.AuthorizationRepository;
import com.example.fatec.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Authorization authorization = authorizationRepository.findByName("ROLE_USER");

        user.setAuthorizationSet(new HashSet<>());
        user.getAuthorizationSet().add(authorization);

        userRepository.save(user);

        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
