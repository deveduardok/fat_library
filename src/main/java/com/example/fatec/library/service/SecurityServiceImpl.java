package com.example.fatec.library.service;

import com.example.fatec.library.entity.Authorization;
import com.example.fatec.library.entity.User;
import com.example.fatec.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder().username(username).password(user.getPassword())
                .authorities(user.getAuthorizationSet().stream()
                        .map(Authorization::getName).collect(Collectors.toList())
                        .toArray(new String[user.getAuthorizationSet().size()]))
                .build();
    }
}
