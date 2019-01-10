package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.UserDataDto;
import com.engineer.lrogozinski.exceptions.ServiceException;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findById(Integer id) throws ServiceException;

    Account save(Account object);

    void delete(Account object);

    void deleteById(Integer id);

    Account findByUsername(String username);

    void deleteByUsername(String username);

    void deleteAccountByUsername(String username);

    Account updateUser(String username , UserDataDto userDataDto);

}