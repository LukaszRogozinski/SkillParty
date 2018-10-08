package com.engineer.lrogozinski.services.security;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;

import java.util.List;

public interface UserService {

    Account save(AccountDto user);
    List<Account> findAll();
    void delete(Integer id);
    Account findOne(String username);

    Account findById(Integer id);
}