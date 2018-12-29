package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.UserDataDto;
import com.engineer.lrogozinski.dto.UserInfo;
import com.engineer.lrogozinski.dto.converter.AccountToUserInfo;
import com.engineer.lrogozinski.dto.converter.UserDataDtoToUserData;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final AccountService accountService;

    private AccountToUserInfo accountToUserInfo;

    private final UserDataDtoToUserData userDataDtoToUserData;

    public UserController(AccountService accountService, AccountToUserInfo accountToUserInfo, UserDataDtoToUserData userDataDtoToUserData) {
        this.accountService = accountService;
        this.accountToUserInfo = accountToUserInfo;
        this.userDataDtoToUserData = userDataDtoToUserData;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<UserInfo> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value="/detail/{username}", method = RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfoByUsername(@PathVariable(value = "username") String username){
        return accountToUserInfo.convert(userService.findOne(username));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE)
    @Transactional
    public void deleteUserByUsername(@PathVariable(value = "username") String username){
            accountService.deleteAccountByUsername(username);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/update/{username}", method = RequestMethod.PUT)
    @Transactional
    public Account updateUser(@PathVariable(value = "username") String username ,@RequestBody UserDataDto userDataDto){
        return accountService.updateUser(username, userDataDto);
    }
}