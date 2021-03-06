package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.UserDataDto;
import com.engineer.lrogozinski.dto.converter.UserDataDtoToUserData;
import com.engineer.lrogozinski.exceptions.ServiceException;
import com.engineer.lrogozinski.repositories.AccountRepository;
import com.engineer.lrogozinski.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.engineer.lrogozinski.exceptions.ExceptionsMessage.CANNOT_FIND_ACCOUNT_WITH_PROVIDED_ID;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserDataDtoToUserData userDataDtoToUserData;

    public AccountServiceImpl(AccountRepository accountRepository, UserDataDtoToUserData userDataDtoToUserData) {
        this.accountRepository = accountRepository;
        this.userDataDtoToUserData = userDataDtoToUserData;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new ServiceException(CANNOT_FIND_ACCOUNT_WITH_PROVIDED_ID));
    }

    @Override
    public Account save(Account object)
    {
        return accountRepository.save(object);
    }

    @Override
    public void delete(Account object) {
        accountRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void deleteByUsername(String username) {
        accountRepository.deleteByUsername(username);
    }

    @Override
    public void deleteAccountByUsername(String username) {
         accountRepository.deleteAccountByUsername(username);
    }

    @Override
    public Account updateUser(String username, UserDataDto userDataDto) {
        Account account =  accountRepository.findByUsername(username);
        account.setUserData(userDataDtoToUserData.convert(userDataDto));
        return accountRepository.save(account);
    }
}
