package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        accountService.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(Integer id) {
        accountService.deleteById(id);
    }

    @Override
    public Account findOne(String username) {
        return accountService.findByUsername(username);
    }

    @Override
    public Account findById(Integer id) {
        return accountService.findById(id);
    }

    @Override
    public Account save(AccountDto user) {
        Account newUser = new Account();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        return accountService.save(newUser);
    }
}

