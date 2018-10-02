package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.repositories.AccountRepository;
import com.engineer.lrogozinski.services.AccountService;
import com.engineer.lrogozinski.services.security.EncryptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private EncryptionService encryptionService;

    public AccountServiceImpl(AccountRepository accountRepository, EncryptionService encryptionService) {
        this.accountRepository = accountRepository;
        this.encryptionService = encryptionService;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account save(Account object)
    {
        if(object.getPassword() != null){
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }
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
}
