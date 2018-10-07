package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.repositories.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Account account =  accountRepository.findByUsername(name);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        account.getRoles().forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRole())));
        return new User(account.getUsername(), account.getEncryptedPassword(),  simpleGrantedAuthorities);
    }
}
