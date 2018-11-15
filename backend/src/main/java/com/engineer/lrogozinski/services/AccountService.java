package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findById(Integer id);

    Account save(Account object);

    void delete(Account object);

    void deleteById(Integer id);

    Account findByUsername(String username);

    void deleteByUsername(String username);

    void deleteAccountByUsername(String username);

}
