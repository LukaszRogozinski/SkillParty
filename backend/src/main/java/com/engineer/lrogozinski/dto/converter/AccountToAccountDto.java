package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Account;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountDto implements Converter<Account, UserDetails> {

    @Override
    public UserDetails convert(Account account) {
        return null;
    }
}
