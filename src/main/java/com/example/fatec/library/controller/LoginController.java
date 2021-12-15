package com.example.fatec.library.controller;

import com.example.fatec.library.security.JwtUtils;
import com.example.fatec.library.security.Login;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
    public Login authenticate(@RequestBody Login login) throws JsonProcessingException {
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        auth = authenticationManager.authenticate(auth);
        login.setPassword(null);
        login.setToken(JwtUtils.generateToken(auth));
        return login;
    }

}
