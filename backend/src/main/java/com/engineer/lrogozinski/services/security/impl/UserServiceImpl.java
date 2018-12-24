package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.domain.Role;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.dto.UserInfo;
import com.engineer.lrogozinski.dto.converter.AccountToUserInfo;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.RoleService;
import com.engineer.lrogozinski.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private AccountToUserInfo accountToUserInfo;

    @Autowired
    private RoleService roleService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(Account user) {
        List authorities = new ArrayList();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });
        return authorities;
    }

    public List<UserInfo> findAll() {
        List<UserInfo> list = new ArrayList<>();
        accountService.findAll().forEach(account -> {
            list.add(accountToUserInfo.convert(account));
        });
        return list;
    }

    @Override
    public void delete(Integer id) {
        accountService.deleteById(id);
    }

    @Override
    public void deleteByUsername(String username) {accountService.deleteByUsername(username);}

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
        newUser.setUserData(user.getUserdata());
        addUserRole(newUser);
        return accountService.save(newUser);
    }

    public Account addUserRole(Account account){
        Role user = roleService.findByRole("USER");
        account.getRoles().add(user);
        return account;
    }
}

