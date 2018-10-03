package com.engineer.lrogozinski.converters;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountToUserDetails implements Converter<Account, UserDetails> {

    @Override
    public UserDetails convert(Account account) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(account.getUsername());
        userDetails.setPassword(account.getEncryptedPassword());
        userDetails.setEnabled(account.getActive());

        List<SimpleGrantedAuthority> authorities = new ArrayList();

        account.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });

        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}
