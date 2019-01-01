package com.engineer.lrogozinski.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDataDto {

    private String name;
    private String surname;
    private String email;
    private String city;
    private String street;
    private Integer houseNo;
    private Integer flatNo;
    private String phoneNo;
}
