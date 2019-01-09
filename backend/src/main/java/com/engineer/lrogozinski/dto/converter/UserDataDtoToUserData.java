package com.engineer.lrogozinski.dto.converter;

import com.engineer.lrogozinski.domain.UserData;
import com.engineer.lrogozinski.dto.UserDataDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDataDtoToUserData implements Converter<UserDataDto,UserData> {

    @Override
    public UserData convert(UserDataDto userDataDto) {
        UserData userData = new UserData();
        userData.setName(userDataDto.getName());
        userData.setSurname(userDataDto.getSurname());
        userData.setEmail(userDataDto.getEmail());
        userData.setCity(userDataDto.getCity());
        userData.setStreet(userDataDto.getStreet());
        userData.setHouseNo(userDataDto.getHouseNo());
        userData.setFlatNo(userDataDto.getFlatNo());
        userData.setPhoneNo(userDataDto.getPhoneNo());
        return userData;
    }
}