package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.dto.converter.AccountToLoggedUser;
import com.engineer.lrogozinski.dto.security.LoggedUser;
import com.engineer.lrogozinski.repositories.AccountRepository;
import com.engineer.lrogozinski.services.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountToLoggedUser accountToLoggedUser;

    @Override
    public LoggedUser getLoggedUserInfo(String username) {
        return accountToLoggedUser.convert(accountRepository.findByUsername(username));
    }
}
