package com.engineer.lrogozinski.repositories;

import com.engineer.lrogozinski.domain.UserData;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<UserData, Integer> {
}
