package com.engineer.lrogozinski.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NewUserDto {

    String username;
    String password;
    String name;
    String surname;
    String email;
    String city;
    String street;
    Integer houseNo;
    Integer flatNo;
    String phoneNo;
}