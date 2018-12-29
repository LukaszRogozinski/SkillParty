package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.NewUserDto;
import com.engineer.lrogozinski.dto.converter.NewUserDtoToAccountDto;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    private final NewUserDtoToAccountDto newUserDtoToAccountDto;

    public RegisterController(UserService userService, NewUserDtoToAccountDto newUserDtoToAccountDto) {
        this.userService = userService;
        this.newUserDtoToAccountDto = newUserDtoToAccountDto;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @Transactional
    public Account registerNewUser(@RequestBody NewUserDto newUserDto) {
           return userService.save(newUserDtoToAccountDto.convert(newUserDto));
    }
}
