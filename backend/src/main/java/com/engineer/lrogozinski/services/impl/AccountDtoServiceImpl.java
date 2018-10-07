package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.dto.converter.AccountDtoToAccount;
import com.engineer.lrogozinski.dto.converter.AccountToAccountDto;
import com.engineer.lrogozinski.repositories.AccountRepository;
import com.engineer.lrogozinski.services.AccountDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDtoServiceImpl implements AccountDtoService {

    private AccountRepository accountRepository;

    private AccountToAccountDto accountToAccountDto;

    private AccountDtoToAccount accountDtoToAccount;

    public AccountDtoServiceImpl(AccountRepository accountRepository, AccountToAccountDto accountToAccountDto, AccountDtoToAccount accountDtoToAccount) {
        this.accountRepository = accountRepository;
        this.accountToAccountDto = accountToAccountDto;
        this.accountDtoToAccount = accountDtoToAccount;
    }

    @Override
    public AccountDto findByUsername(String username) {
        return accountToAccountDto.convert(accountRepository.findByUsername(username));
    }

    @Override
    public List<AccountDto> findAll() {
        List<AccountDto> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(account -> accountList.add(accountToAccountDto.convert(account)));
        return accountList;
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountToAccountDto.convert(accountRepository.findById(id).orElse(null));
    }

    @Override
    public AccountDto save(AccountDto object) {
        accountRepository.save(accountDtoToAccount.convert(object));
        return object;
    }

    @Override
    public void delete(AccountDto object) {
        accountRepository.delete(accountDtoToAccount.convert(object));
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }
}
