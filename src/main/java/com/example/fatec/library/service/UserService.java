package com.example.fatec.library.service;

import com.example.fatec.library.dto.UserDto;
import com.example.fatec.library.entity.User;

public interface UserService {

    User registerUser(UserDto userDto);

}
