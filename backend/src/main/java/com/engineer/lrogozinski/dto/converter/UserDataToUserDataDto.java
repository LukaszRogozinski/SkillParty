package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.UserDataDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserDataDto implements Converter<UserData, UserDataDto> {
    @Override
    public UserDataDto convert(UserData userData) {
        return UserDataDto.builder()
                .city(userData.getCity())
                .email(userData.getEmail())
                .flatNo(userData.getFlatNo())
                .houseNo(userData.getHouseNo())
                .name(userData.getName())
                .street(userData.getStreet())
                .surname(userData.getSurname())
                .build();
    }
}
