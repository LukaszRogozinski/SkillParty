package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.UserInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountToUserInfo implements Converter<Account, UserInfo> {

    @Override
    public UserInfo convert(Account account) {

        List<String> favouriteEventCategories = new ArrayList<>();
        account.getUserData().getFavouriteEventCategories().forEach(eventCategory -> favouriteEventCategories.add(eventCategory.getName()));

        return UserInfo.builder()
                .username(account.getUsername())
                .name(account.getUserData().getName())
                .surname(account.getUserData().getSurname())
                .email(account.getUserData().getEmail())
                .city(account.getUserData().getCity())
                .street(account.getUserData().getStreet())
                .houseNo(account.getUserData().getHouseNo())
                .flatNo(account.getUserData().getFlatNo())
                .phoneNo(account.getUserData().getPhoneNo())
                .favouriteCategories(favouriteEventCategories)
                .build();
    }
}
