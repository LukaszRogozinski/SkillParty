package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByUsername(String username);
}
