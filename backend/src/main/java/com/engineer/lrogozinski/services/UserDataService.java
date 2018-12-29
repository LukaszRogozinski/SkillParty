package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.domain.UserData;

import java.util.List;

public interface UserDataService {

    List<UserData> findAll();

    UserData findById(Integer id);

    UserData save(UserData object);

    void delete(UserData object);

    void deleteById(Integer id);

    UserData findByUsername(String username);
}
