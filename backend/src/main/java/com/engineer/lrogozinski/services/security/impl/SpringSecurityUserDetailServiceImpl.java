package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class SpringSecurityUserDetailServiceImpl implements UserDetailsService {

    private AccountService accountService;
    private Converter<Account,UserDetails> accountToUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountToUserDetailsConverter.convert(accountService.findByUsername(username));
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    @Autowired
    @Qualifier(value = "accountToUserDetails")
    public void setAccountToUserDetailsConverter(Converter<Account, UserDetails> accountToUserDetailsConverter) {
        this.accountToUserDetailsConverter = accountToUserDetailsConverter;
    }
}
