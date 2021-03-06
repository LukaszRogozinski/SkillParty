package com.engineer.lrogozinski.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserInfo {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String street;
    private Integer houseNo;
    private Integer flatNo;
    private String phoneNo;
    private List<String> favouriteCategories = new ArrayList<>();
}