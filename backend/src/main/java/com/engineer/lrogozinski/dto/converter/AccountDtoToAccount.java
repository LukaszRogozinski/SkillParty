package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoToAccount implements Converter<AccountDto, Account> {

    @Override
    public Account convert(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setUserData(accountDto.getUserdata());
        return account;
    }
}
