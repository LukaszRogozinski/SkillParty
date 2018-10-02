package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.AccountDto;

public interface AccountService extends CrudService<Account, Integer> {
    Account findByUsername(String username);
}
