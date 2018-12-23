package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.Account;
import com.engineer.lrogozinski.dto.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByUsername(String username);
    void deleteAccountByUsername(String username);
    void deleteByUsername(String username);
    Account findAccountsByUserDataFavouriteEventCategories(String eventCategory);
}
