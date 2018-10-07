package com.engineer.lrogozinski.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDto {

    private Integer id;
    private String username;
    private String password;
}
