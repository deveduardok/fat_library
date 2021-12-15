package com.example.fatec.library.controller;

import com.example.fatec.library.dto.UserDto;
import com.example.fatec.library.entity.User;
import com.example.fatec.library.service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    @JsonView(View.User.class)
    public ResponseEntity<Collection<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(View.User.class)
    public ResponseEntity<User> postBook(@RequestBody UserDto userDto, UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.registerUser(userDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/user/" + user.getId()).build().toUri());

        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

}
