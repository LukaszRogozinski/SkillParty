package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.UserInfo;
import com.engineer.lrogozinski.dto.converter.AccountToUserInfo;
import com.engineer.lrogozinski.services.UserDataService;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.engineer.lrogozinski.security.Constants.HEADER_STRING;
import static com.engineer.lrogozinski.security.Constants.TOKEN_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    private final JwtTokenUtil jwtTokenUtil;

    private AccountToUserInfo accountToUserInfo;


    public UserController( JwtTokenUtil jwtTokenUtil, AccountToUserInfo accountToUserInfo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountToUserInfo = accountToUserInfo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<UserInfo> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Account getOne(@PathVariable(value = "id") Integer id){
        return userService.findById(id);
    }


    @RequestMapping(value="/detail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserInfo getLoggedUserInfo(HttpServletRequest req){
        String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
        return accountToUserInfo.convert(userService.findOne(jwtTokenUtil.getUsernameFromToken(token)));
    }
}