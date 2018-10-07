package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountDto implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getEncryptedPassword())
                .build();
    }
}
