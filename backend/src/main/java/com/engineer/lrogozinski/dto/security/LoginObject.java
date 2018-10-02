package com.engineer.lrogozinski.dto.security;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginObject {
    private String login;
    private String password;
}
