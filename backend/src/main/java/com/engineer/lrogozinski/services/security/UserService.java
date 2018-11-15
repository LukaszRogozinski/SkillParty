package com.engineer.lrogozinski.services.security;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.dto.UserInfo;

import java.util.List;

public interface UserService {

    Account save(AccountDto user);
    List<UserInfo> findAll();
    void delete(Integer id);
    void deleteByUsername(String username);
    Account findOne(String username);
    Account findById(Integer id);
}