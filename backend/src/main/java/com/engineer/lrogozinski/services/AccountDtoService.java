package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.dto.AccountDto;

public interface AccountDtoService extends CrudService<AccountDto, Integer> {
    AccountDto findByUsername(String username);
}
