package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.AccountDto;
import com.engineer.lrogozinski.dto.NewUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewUserDtoToAccountDto implements Converter<NewUserDto, AccountDto> {

    @Override
    public AccountDto convert(NewUserDto newUserDto) {

        UserData userData = new UserData();
        userData.setName(newUserDto.getName());
        userData.setSurname(newUserDto.getSurname());
        userData.setEmail(newUserDto.getEmail());
        userData.setCity(newUserDto.getCity());
        userData.setStreet(newUserDto.getStreet());
        userData.setHouseNo(newUserDto.getHouseNo());
        userData.setFlatNo(newUserDto.getFlatNo());
        userData.setPhoneNo(newUserDto.getPhoneNo());

        return AccountDto.builder()
                .username(newUserDto.getUsername())
                .password(newUserDto.getPassword())
                .userdata(userData)
                .build();
    }
}