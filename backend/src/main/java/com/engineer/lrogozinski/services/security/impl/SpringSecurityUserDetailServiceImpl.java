package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.services.AccountService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class SpringSecurityUserDetailServiceImpl implements UserDetailsService {

    private AccountService accountService;
    private Converter<Account,UserDetails> accountToUserDetailsConverter;

    public SpringSecurityUserDetailServiceImpl(AccountService accountService, Converter<Account, UserDetails> accountToUserDetailsConverter) {
        this.accountService = accountService;
        this.accountToUserDetailsConverter = accountToUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountToUserDetailsConverter.convert(accountService.findByUsername(username));
    }
}
