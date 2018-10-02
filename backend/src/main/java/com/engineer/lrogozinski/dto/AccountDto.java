package com.engineer.lrogozinski.dto;

import com.engineer.lrogozinski.domain.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountDto {

    private String username;
    private String password_hash;
    private Boolean verified;
    private Boolean active;
    private Collection<Role> roles = new ArrayList<>();
}
