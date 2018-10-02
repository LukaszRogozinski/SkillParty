package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.domain.Role;
import com.engineer.lrogozinski.dto.security.LoggedUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountToLoggedUser implements Converter<Account, LoggedUser> {
    @Override
    public LoggedUser convert(Account account) {

        return LoggedUser.builder()
                .roles(account.getRoles()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toList()))
                .build();
    }
}
